package com.ptteng.shiro.filter;

import com.ptteng.statics.ResponseJson;
import com.ptteng.utlis.ShiroUtil;
import org.apache.shiro.SecurityUtils;
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
        //如果是登录请求（其实这一步判断是冗余的，可以通过URL规则避免）,则不受拦截
        if (isLoginRequest(request, response) || SecurityUtils.getSubject().getPrincipal() != null) {
            return true;
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
