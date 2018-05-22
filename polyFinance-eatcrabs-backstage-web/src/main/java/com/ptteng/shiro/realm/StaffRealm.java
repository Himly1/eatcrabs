package com.ptteng.shiro.realm;

import com.ptteng.bo.BackstagePermsBO;
import com.ptteng.domain.manager.Module;
import com.ptteng.domain.manager.Staff;
import com.ptteng.dubbo.BackstageConsumer;
import com.ptteng.utlis.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * staff授权器
 */
@Component
public class StaffRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(StaffRealm.class);

    @Autowired
    private BackstageConsumer consumer;

    /**
     * 验证当前登录的Subject
     * 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        //两个token的引用都是一样的
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息
        //这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证
        Staff staff = consumer.getManagerService().getStaffLoginToken(token.getUsername());
        if (null != staff) {
            logger.info("获取到的staff信息：" + staff.toString());
            ShiroUtil.addValue(SecurityUtils.getSubject(), "name", staff.getAccount());
            ShiroUtil.addValue(SecurityUtils.getSubject(), "id", staff.getId());
            return new SimpleAuthenticationInfo(token.getUsername(), staff.getKey(), "staffReaml");
        } else {
            throw new UnknownAccountException();
        }
    }

    /**
     * 为当前登录的Subject授予角色和权限
     * 经测试:本例中该方法的调用时机为需授权资源被访问时
     * 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     * 本项目已经成功持久化到缓存，本次方法只会调用一次，大大减少数据库负担
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        if (!principalCollection.getRealmNames().contains("staffReaml")) {
            simpleAuthorInfo.addRole("NULL");
            simpleAuthorInfo.addStringPermission("NULL");
            return simpleAuthorInfo;
        }
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
        String currentUsername = (String) super.getAvailablePrincipal(principalCollection);
        //从数据库中获取用户属性、角色属性、权限属性
        BackstagePermsBO staffPermsBO = consumer.getManagerService().getStaffPerms(currentUsername);
        logger.info("获取到Staff权限属性:{}，用户名字：{}", staffPermsBO, currentUsername);
        if (staffPermsBO == null) {
            // 这里不能直接返回null，不然会使缓存策略失效
            simpleAuthorInfo.addRole("NULL");
            simpleAuthorInfo.addStringPermission("NULL");
        } else {
            simpleAuthorInfo.addRole(staffPermsBO.getRoleName());
            for (Module module : staffPermsBO.getPerms()) {
                simpleAuthorInfo.addStringPermission(module.getUrl());
            }
        }
        return simpleAuthorInfo;
    }

}