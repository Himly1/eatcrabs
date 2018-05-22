package com.ptteng.utlis.jpa;

import org.springframework.data.domain.Sort;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 自动分页实现标签
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoPageable {

    //排序方式
    Sort.Direction direction() default Sort.Direction.DESC;

    //排序对应数据库里面的字段
    String fieldName() default "";

}
