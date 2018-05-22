package com.ptteng.shiro.manager;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * shiro 会话监听器
 */
@Component
public class ShiroSessionListener implements SessionListener {
    private static final Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);

    @Resource
    private RedisSessionDAO redisSessionDAO;

    /**
     * 一个回话的生命周期开始
     */
    @Override
    public void onStart(Session session) {
    }

    /**
     * 一个回话的生命周期结束
     */
    @Override
    public void onStop(Session session) {
    }

    @Override
    public void onExpiration(Session session) {
        redisSessionDAO.doDelete(session);
    }

}
