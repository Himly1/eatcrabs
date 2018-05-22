package com.ptteng.mvc.handle;

import com.ptteng.exception.InsideException;
import com.ptteng.exception.SessionException;
import com.ptteng.statics.ResponseJson;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 用来处理一些意料中的异常
 */
@Order(200)
@RestControllerAdvice
public class NormalHandle {
    private static final Logger logger = LoggerFactory.getLogger(NormalHandle.class);

    @ExceptionHandler(value = SessionException.class)
    public String badSessionHandle(SessionException exception) {
        SecurityUtils.getSubject().logout();
        logger.warn("异常的会话：" + exception.getMessage());
        return ResponseJson.NOT_LOGIN_MESSAGE;
    }

    @ExceptionHandler(value = InsideException.class)
    public String badOperationHandle(InsideException exception) {
        logger.warn("业务异常：" + exception.getMessage());
        return ResponseJson.ERROR_MESSAGE;
    }

}