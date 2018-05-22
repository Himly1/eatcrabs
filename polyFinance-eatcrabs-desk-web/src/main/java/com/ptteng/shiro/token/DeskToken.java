package com.ptteng.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义登录令牌
 */
public class DeskToken extends UsernamePasswordToken {

    public enum LoginType {
        PASSWORD, GESTURE
    }

    private LoginType loginType;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public DeskToken(String username, String password, LoginType type) {
        super(username, password);
        loginType = type;
    }

    public DeskToken(String username, String password, LoginType type, boolean rememberMe) {
        super(username, password, rememberMe);
        loginType = type;
    }

}
