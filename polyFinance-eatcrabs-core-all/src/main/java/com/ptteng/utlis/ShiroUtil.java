package com.ptteng.utlis;

import com.ptteng.exception.SessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/**
 * Shiro工具类
 */
public class ShiroUtil {
    private static final Logger logger = LoggerFactory.getLogger(ShiroUtil.class);

    public static boolean isAjax(ServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }

    public static void out(ServletResponse response, String json) {

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(json);
        } catch (Exception e) {
            logger.warn("exception on outing json");
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }

    public static void addValue(Subject subject, Object key, Object value) {
        Session session = subject.getSession();
        if (session == null) {
            throw new SessionException("会话过期");
        }
        session.setAttribute(key, value);
    }

}
