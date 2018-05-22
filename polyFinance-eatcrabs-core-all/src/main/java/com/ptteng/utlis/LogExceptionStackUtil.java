package com.ptteng.utlis;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 日志打印异常堆栈信息工具类
 */
public class LogExceptionStackUtil {
    public static String LogExceptionStack(Throwable e) {
        StringWriter errorsWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(errorsWriter));
        return errorsWriter.toString();
    }
}
