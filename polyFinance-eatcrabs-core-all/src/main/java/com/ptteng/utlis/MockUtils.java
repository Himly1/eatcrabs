package com.ptteng.utlis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 假数据工具类
 */
public class MockUtils {
    private static final Logger logger = LoggerFactory.getLogger(MockUtils.class);
    public static final int COUNT = 20;
    private static final int SIZE = 10;

    public static <T extends Serializable> T getMockDomain(Class<T> domainClass) {
        T domain = null;
        try {
            domain = domainClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.warn("生成实例失败：" + domainClass.getName());
        }
        Field[] fields = domainClass.getDeclaredFields();
        Method m;
        String name, type;
        // 遍历所有属性
        try {
            for (Field field : fields) {
                // 获取属性的名字
                name = field.getName();
                // 将属性的首字符大写，方便使用get，set方法
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                // 获取属性的类型
                type = field.getGenericType().getTypeName();
                if (type.equals("java.lang.String")) {
                    m = domainClass.getDeclaredMethod("set" + name, String.class);
                    m.invoke(domain, "测试字符串");
                    continue;
                }
                if (type.equals("java.lang.Integer")) {
                    m = domainClass.getDeclaredMethod("set" + name, Integer.class);
                    m.invoke(domain, 1);
                    continue;
                }
                if (type.equals("java.lang.Long")) {
                    m = domainClass.getDeclaredMethod("set" + name, Long.class);
                    m.invoke(domain, 1L);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("设置属性失败，错误类型：" + e.getClass().getName() + " ,类名：" + domainClass.getName());
        }
        return domain;
    }

    public static <T extends Serializable> List<T> getMockDomainPage(Class<T> domainClass) {
        T domain = getMockDomain(domainClass);
        List<T> list = new LinkedList<>();
        for (int i = 0; i < SIZE; i++) {
            list.add(domain);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T getMockBO(Class<T> boClass){
        T bo = null;
        try {
            bo = boClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.warn("生成实例失败：" + boClass.getName());
        }
        Field[] fields = boClass.getDeclaredFields();
        Serializable domain;
        Method m;
        Class domainClass;
        String name, type;
        try {
            for (Field field : fields) {
                // 获取属性的名字
                name = field.getName();
                // 将属性的首字符大写，方便使用get，set方法
                name = name.substring(0, 1).toUpperCase() + name.substring(1);
                // 获取属性的类型
                type = field.getGenericType().getTypeName();
                if (type.equals("java.lang.String")) {
                    m = boClass.getDeclaredMethod("set" + name, String.class);
                    m.invoke(bo, "测试字符串");
                    continue;
                }
                if (type.equals("java.lang.Integer")) {
                    m = boClass.getDeclaredMethod("set" + name, Integer.class);
                    m.invoke(bo, 1);
                    continue;
                }
                if (type.equals("java.lang.Long")) {
                    m = boClass.getDeclaredMethod("set" + name, Long.class);
                    m.invoke(bo, 1L);
                    continue;
                }
                //如果不是基础类型，则尝试domain对象
                domainClass = Class.forName(type);
                domain = getMockDomain(domainClass);
                m = boClass.getDeclaredMethod("set" + name, domainClass);
                m.invoke(bo, domain);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("设置属性失败，错误类型：" + e.getClass().getName() + " ,类名：" + boClass.getName());
        }
        return bo;
    }

    public static <T extends Serializable> List<T> getMockBOPage(Class<T> boClass) {
        T bo = getMockBO(boClass);
        List<T> list = new LinkedList<>();
        for (int i = 0; i < SIZE; i++) {
            list.add(bo);
        }
        return list;
    }

}
