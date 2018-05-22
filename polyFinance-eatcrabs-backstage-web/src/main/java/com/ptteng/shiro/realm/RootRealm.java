package com.ptteng.shiro.realm;

import com.ptteng.domain.manager.Root;
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
 * root授权器
 */
@Component
public class RootRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(RootRealm.class);

    @Autowired
    private BackstageConsumer consumer;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        Root root = consumer.getManagerService().getRootLoginToken(token.getUsername());
        if (null != root) {
            logger.info("获取到的root信息：" + root.toString());
            ShiroUtil.addValue(SecurityUtils.getSubject(), "name", root.getAccount());
            ShiroUtil.addValue(SecurityUtils.getSubject(), "id", 0L);
            return new SimpleAuthenticationInfo(token.getUsername(), root.getKey(), "rootReaml");
        } else {
            throw new UnknownAccountException();
        }
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        if (!principalCollection.getRealmNames().contains("rootReaml")) {
            simpleAuthorInfo.addRole("NULL");
            simpleAuthorInfo.addStringPermission("NULL");
            return simpleAuthorInfo;
        }
        simpleAuthorInfo.addRole("root");
        simpleAuthorInfo.addStringPermission("ALL");
        return simpleAuthorInfo;
    }
}
