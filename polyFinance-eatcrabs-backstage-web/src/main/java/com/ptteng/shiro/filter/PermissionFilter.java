package com.ptteng.shiro.filter;

import com.ptteng.statics.ResponseJson;
import com.ptteng.utlis.ShiroUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * shiro 权限拦截器
 */
public class PermissionFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject.hasRole("root")) {
            return true;
        }
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        //获取整个请求的
        String uri = httpRequest.getRequestURI();
        //获取basePath(用于替换)
        String basePath = httpRequest.getContextPath();
        //替换掉前面不需要的部分
        if (null == uri || !uri.startsWith(basePath)) {
            return false;
        }
        uri = uri.replace(basePath, "");
        String[] parts = uri.split("/");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String part : parts) {
            sb.append(part);
            sb.append("/");
            if (++i > 2) {
                break;
            }
        }
        // 以”/“为拆分，获取到权限判断用的URL
        if (subject.isPermitted(sb.toString())) {
            return true;
        }
        if (ShiroUtil.isAjax(request)) {
            ShiroUtil.out(response, ResponseJson.NOT_PERM_MESSAGE);
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
