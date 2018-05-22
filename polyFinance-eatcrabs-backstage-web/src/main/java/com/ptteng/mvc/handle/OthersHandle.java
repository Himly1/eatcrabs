package com.ptteng.mvc.handle;

import com.ptteng.statics.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 用来处理意料外的异常
 */
@Order(100000)
@RestControllerAdvice
public class OthersHandle {
    private static final Logger logger = LoggerFactory.getLogger(OthersHandle.class);

    @ExceptionHandler(value = Exception.class)
    public String otherExceptionsHandle(Exception exception) {
        String message = exception.getMessage();
        if (message.startsWith("org.springframework.dao.DataIntegrityViolationException")) {
            return ResponseJson.BAD_REQUEST_MESSAGE;
        }
        logger.error("意料外的异常：" + message);
        return ResponseJson.ERROR_MESSAGE;
    }

}
