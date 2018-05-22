package com.ptteng.utlis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取Ip信息的工具类
 */
public class IPUtil {

    private static final Logger logger = LoggerFactory.getLogger(IPUtil.class);

    public static String getIpAddr(HttpServletRequest request) {
        //https://www.cnblogs.com/final-zhang/p/5473853.html
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        //如果只有一级代理的话，其实和x-forwarded-for基本上等效
        ip = request.getHeader("X-Real-IP");
        if (ip != null) {
            return ip;
        }

        return request.getRemoteAddr();
    }

    //判断是否是黑名单里面的ip,待完善
    public static boolean blackList() {
        return false;
    }

    //添加黑名单，待完善

}
