package com.ptteng.shiro.filter;

import com.ptteng.statics.ResponseJson;
import com.ptteng.utlis.ShiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * shiro 登录拦截器
 */
public class LoginFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        //注意，如果用户是通过账户密码登录的，这一步判断才为true!
        if (subject.isAuthenticated()) {
            return true;
        }
        //判断用户是否带着rememberMe cookie
        if (subject.isRemembered()) {
            if (ShiroUtil.isAjax(request)) {
                ShiroUtil.out(response, ResponseJson.NEED_GESTURE);
            }
            return false;
        }
        //session过期，账号被冻结，或者没有登录，就返回-2状态码
        if (ShiroUtil.isAjax(request)) {
            ShiroUtil.out(response, ResponseJson.NOT_LOGIN_MESSAGE);
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (!ShiroUtil.isAjax(request)) {
            WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        return false;
    }
}
