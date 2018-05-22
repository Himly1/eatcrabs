package com.ptteng.shiro.auth;

import com.ptteng.shiro.realm.RootRealm;
import com.ptteng.shiro.realm.StaffRealm;
import com.ptteng.shiro.token.BackstageToken;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * shiro 多reaml调度器
 */
@Component
public class ModularAuth extends ModularRealmAuthenticator {
    @Autowired
    private StaffRealm staffRealm;
    @Autowired
    private RootRealm rootRealm;

    /**
     * 多个realm实现
     */
    @Override
    protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) {
        return super.doMultiRealmAuthentication(realms, token);
    }

    /**
     * 调用单个realm执行操作
     */
    @Override
    protected AuthenticationInfo doSingleRealmAuthentication(Realm realm, AuthenticationToken token) {
        return realm.getAuthenticationInfo(token);
    }

    /**
     * 判断登录类型执行操作
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        this.assertRealmsConfigured();

        Realm realm = null;

        BackstageToken token = (BackstageToken) authenticationToken;
        if (token.getLoginType().equals(BackstageToken.LoginType.STAFF)) {
            realm = staffRealm;
        }
        if (token.getLoginType().equals(BackstageToken.LoginType.ROOT)) {
            realm = rootRealm;
        }
        if (realm == null) {
            return null;
        }

        return this.doSingleRealmAuthentication(realm, authenticationToken);
    }

    /**
     * 判断realm是否为空
     */
    @Override
    protected void assertRealmsConfigured() throws IllegalStateException {
    }
}
