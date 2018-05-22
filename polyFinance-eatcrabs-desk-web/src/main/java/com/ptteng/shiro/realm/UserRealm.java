package com.ptteng.shiro.realm;

import com.ptteng.domain.business.User;
import com.ptteng.dubbo.DeskConsumer;
import com.ptteng.shiro.token.DeskToken;
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
        //具体细节可以查看后台模块注解
        DeskToken token = (DeskToken) authenticationToken;
        User user = consumer.getCustomerService().getUserLoginToken(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException();
        } else if (user.getFreeze() == -1) {
            throw new DisabledAccountException();
        } else {
            logger.info("获取到的user信息：" + user.toString());
            String key = token.getLoginType().equals(DeskToken.LoginType.PASSWORD) ? user.getKey() : user.getGesture();
            return new SimpleAuthenticationInfo(token.getUsername(), key, "userReaml");
        }
    }
}
