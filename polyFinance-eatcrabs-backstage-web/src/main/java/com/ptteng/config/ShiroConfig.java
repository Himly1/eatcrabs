package com.ptteng.config;

import com.ptteng.redis.RedisCacheManager;
import com.ptteng.shiro.auth.ModularAuth;
import com.ptteng.shiro.filter.LoginFilter;
import com.ptteng.shiro.filter.PermissionFilter;
import com.ptteng.shiro.manager.RedisSessionDAO;
import com.ptteng.shiro.manager.ShiroSessionListener;
import com.ptteng.shiro.realm.RootRealm;
import com.ptteng.shiro.realm.StaffRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.*;

/**
 * Shiro 配置类
 */
@Configuration
public class ShiroConfig {

    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    //如果不配置，会报错UnavailableSecurityManagerException
    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //注入拦截器实例
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("permission", new PermissionFilter());
        filters.put("login", new LoginFilter());
        shiroFilterFactoryBean.setFilters(filters);
        //注册拦截机制,注意顺序决定优先级，所以必须使用LinkedHashMap
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        /*Shiro连接约束配置,即拦截器链的定义，详情：http://blog.csdn.net/jadyer/article/details/12172839
        拦截器的url配置符合Ant风格，注意*和**的区别
        下面value值的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的
        anon和authc是内置的枚举拦截器，详情见：http://jinnianshilongnian.iteye.com/blog/2025656*/
        filterChainDefinitionMap.put("/a/u/**", "anon");
        filterChainDefinitionMap.put("/a/common/**", "login");
        filterChainDefinitionMap.put("/a/**", "login,permission");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        /*shiroFilterFactoryBean.setFilterChainDefinitions("/a/**=anon\n" +
                "/a/common/**=login\n" +
                "/a/admin/**=login,permission\n");*/
        //Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //此处有坑 默认为http
        shiroFilterFactoryBean.setLoginUrl("/a/u/login");
        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(modularAuth());
        List<Realm> realms = new LinkedList<>();
        realms.add(staffRealm());
        realms.add(rootRealm());
        securityManager.setRealms(realms);
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }

    @Bean
    //自己维护着会话，直接废弃了Servlet容器的会话管理
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        List<SessionListener> listeners = new ArrayList<>();
        listeners.add(new ShiroSessionListener());
        sessionManager.setSessionListeners(listeners);
        /*Shiro提供了会话验证调度器，用于定期的验证会话是否已过期，如果过期将停止会话；
        出于性能考虑，一般情况下都是获取会话时来验证会话是否过期并停止会话的；
        但是如在web环境中，如果用户不主动退出是不知道会话是否过期的，因此需要定期的检测会话是否过期，
        Shiro提供了会话验证调度器SessionValidationScheduler来做这件事情。*/
        sessionManager.setSessionValidationScheduler(sessionValidationScheduler());
        //相隔多久检查一次session的有效性(主动检查) 十小时一次
        sessionManager.setSessionValidationInterval(36000000L);
        //是否开启 检测，默认开启
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //是否删除无效的，默认也是开启
        sessionManager.setDeleteInvalidSessions(true);
        //session 有效时间为五个小时 （毫秒单位）
        sessionManager.setGlobalSessionTimeout(18000000L);
        sessionManager.setSessionDAO(redisSessionDAO());
        //会话Cookie模板
        sessionManager.setSessionIdCookie(sessionIdCookie());
        return sessionManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
        return redisSessionDAO;
    }

    @Bean
    public SessionValidationScheduler sessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        //间隔多少时间检查，不配置是60分钟.如果不配置的话，只有用户请求,我们才知道会话已经过期,而现在shiro可以化被动为主动检查
        scheduler.setInterval(36000000L);
        return scheduler;
    }

    @Bean
    public SimpleCookie sessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        //cookie的name,F12可见
        simpleCookie.setName("backstage");
        /*如果设置为true，则客户端不会暴露给客户端脚本代码，
        使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
        此特性需要实现了Servlet 2.5 MR6及以上版本的规范的Servlet容器支持*/
        simpleCookie.setHttpOnly(true);
        //设置Cookie的过期时间，秒为单位，默认-1表示关闭浏览器时过期Cookie
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    /* 对于SpringBoot，第一步要把ShiroRealm注册为Bean
     * 然后在@Configuration类中调用方法，将其注入授权管理器
     * 然后在授权管理器里面必需调用这个方法注入，不然NPE!*/
    @Bean
    public StaffRealm staffRealm() {
        StaffRealm realm = new StaffRealm();
        realm.setCacheManager(redisCacheManager());
        realm.setCachingEnabled(true);
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthorizationCachingEnabled(true);
        realm.setAuthorizationCacheName("staffPerms");
        realm.setAuthenticationCacheName("staffToken");
        return realm;
    }

    @Bean
    public RootRealm rootRealm() {
        RootRealm realm = new RootRealm();
        realm.setCacheManager(redisCacheManager());
        realm.setAuthorizationCacheName("rootPerms");
        realm.setAuthenticationCacheName("rootToken");
        realm.setCachingEnabled(true);
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthorizationCachingEnabled(true);
        return realm;
    }

    @Bean
    public ModularAuth modularAuth() {
        return new ModularAuth();
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        return new RedisCacheManager();
    }

}
