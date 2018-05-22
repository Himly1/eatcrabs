package com.ptteng.utlis.validator;

import com.alibaba.fastjson.JSON;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

/**
 * Validator参检工具类
 */
public class ValidatorUtil {
    /*failFast的参数：
     * true:一次检测不通过就停止检测，并马上返回结果（也就是结果set的size永远是0或1）
     * false：全部检测，并获得全部结果*/
    private static Validator validator =
            Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    public static <T> String getErrorJSON(T vo) {
        Set<ConstraintViolation<T>> errorInfos = validator.validate(vo, VoGroup.class);
        if (errorInfos.isEmpty()) {
            return null;
        }
        Set<String> set = new HashSet<>();
        for (ConstraintViolation<T> errorInfo : errorInfos) {
            set.add(errorInfo.getPropertyPath().toString() + errorInfo.getMessage());
        }
        return JSON.toJSONString(set);
    }

}
