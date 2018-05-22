package com.ptteng.exception;

/**
 * 由操作session失败导致的异常
 */
public class SessionException extends RuntimeException {
    //异常信息
    private String message;

    public SessionException(String message) {
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
