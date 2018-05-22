package com.ptteng.utlis.jpa;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * JPA更新用实现类
 */
public class CopyUtil {
    public static <T extends Serializable> void copyValue(T vo, T po) {
        String name;
        Method getMethod, setMethod;
        Object value;
        try {
            for (Field field : vo.getClass().getDeclaredFields()) {
                name = field.getName();
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                getMethod = vo.getClass().getDeclaredMethod("get" + name);
                setMethod = po.getClass().getMethod("set" + name, getMethod.getReturnType());
                value = getMethod.invoke(vo);
                if (value == null) {
                    continue;
                }
                setMethod.invoke(po, value);
            }
        } catch (Exception e) {
            throw new RuntimeException("copy属性失败:" + vo.getClass().getSimpleName());
        }
    }
}
