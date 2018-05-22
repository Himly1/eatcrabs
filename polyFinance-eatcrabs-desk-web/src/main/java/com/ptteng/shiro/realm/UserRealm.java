package com.ptteng.shiro.realm;

import com.ptteng.domain.business.User;
import com.ptteng.dubbo.DeskConsumer;
import com.ptteng.utlis.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(UserRealm.class);
    @Autowired
    DeskConsumer consumer;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        //两个token的引用都是一样的
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息
        //这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证
        User user = consumer.getCustomerService().getUserLoginToken(token.getUsername());
        if (null != user) {
            logger.info("获取到的user信息：" + user.toString());
            ShiroUtil.addValue(SecurityUtils.getSubject(), "mobile", user.getAccount());
            ShiroUtil.addValue(SecurityUtils.getSubject(), "id", user.getId());
            return new SimpleAuthenticationInfo(token.getUsername(), user.getKey(), "userReaml");
        } else {
            throw new UnknownAccountException();
        }
    }
}
