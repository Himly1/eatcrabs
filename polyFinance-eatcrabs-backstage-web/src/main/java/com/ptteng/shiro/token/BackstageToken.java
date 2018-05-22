package com.ptteng.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义登录令牌
 */
public class BackstageToken extends UsernamePasswordToken {

    public enum LoginType {
        STAFF, ROOT
    }

    private LoginType loginType;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public BackstageToken(String username, String password, LoginType type) {
        super(username, password);
        loginType = type;
    }

}
