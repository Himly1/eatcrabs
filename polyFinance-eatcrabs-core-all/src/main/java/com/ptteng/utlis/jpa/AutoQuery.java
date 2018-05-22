package com.ptteng.utlis.jpa;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自动查询实现标签
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoQuery {

    //查询类型枚举类
    enum ConditionType {
        MATCH, LIKE, MIN, MAX
    }

    //查询类型
    ConditionType condition() default ConditionType.MATCH;

    //查询对应实体类里面的字段
    String fieldName() default "";
}
