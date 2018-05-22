package com.ptteng.exception;

/**
 * 由某些业务失败导致的异常
 */
public class InsideException extends RuntimeException {
    //异常信息
    private String message;

    public InsideException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
