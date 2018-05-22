package com.ptteng.utlis.jpa;

import com.ptteng.vo.common.BaseQuery;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * JPA自定义查询条件
 */
public class MySpecification<T> implements Specification<T> {

    private BaseQuery query;

    public MySpecification(BaseQuery query) {
        this.query = query;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return getPredicate(root, criteriaBuilder, query);
    }


    private Predicate getPredicate(Root<T> root, CriteriaBuilder criteriaBuilder, BaseQuery query) {
        List<Predicate> list = new LinkedList<>();
        Class<?> voClass = query.getClass();
        //通过反射获取所有的成员变量（继承自父类的不算）
        Field[] fields = voClass.getDeclaredFields();
        String fieldName, voFieldName, voFieldType, voFieldString;
        Method m;
        Long voFieldLong;
        Integer voFieldInteger;
        AutoQuery autoQuery;
        try {
            for (Field field : fields) {
                if (!field.isAnnotationPresent(AutoQuery.class)) {
                    continue;
                }
                autoQuery = field.getAnnotation(AutoQuery.class);
                fieldName = autoQuery.fieldName().equals("") ? field.getName() : autoQuery.fieldName();
                voFieldName = field.getName();
                voFieldName = voFieldName.substring(0, 1).toUpperCase() + voFieldName.substring(1);
                m = voClass.getDeclaredMethod("get" + voFieldName);
                voFieldType = field.getGenericType().getTypeName();

                if (voFieldType.equals("java.lang.String")) {
                    voFieldString = (String) m.invoke(query);
                    if (voFieldString == null || voFieldString.equals("")) {
                        continue;
                    }
                    switch (autoQuery.condition()) {
                        case LIKE:
                            list.add(criteriaBuilder.like(root.get(fieldName).as(String.class), "%" + voFieldString + "%"));
                            break;
                        case MATCH:
                            list.add(criteriaBuilder.equal(root.get(fieldName).as(String.class), voFieldString));
                            break;
                        default:
                            return null;
                    }
                    continue;
                }

                if (voFieldType.equals("java.lang.Long")) {
                    voFieldLong = (Long) m.invoke(query);
                    if (voFieldLong == null || voFieldLong == 0L) {
                        continue;
                    }
                    switch (autoQuery.condition()) {
                        case MIN:
                            list.add(criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName).as(Long.class), voFieldLong));
                            break;
                        case MAX:
                            list.add(criteriaBuilder.lessThanOrEqualTo(root.get(fieldName).as(Long.class), voFieldLong));
                            break;
                        case MATCH:
                            list.add(criteriaBuilder.equal(root.get(fieldName).as(Long.class), voFieldLong));
                            break;
                        default:
                            return null;
                    }
                    continue;
                }

                if (voFieldType.equals("java.lang.Integer")) {
                    voFieldInteger = (Integer) m.invoke(query);
                    if (voFieldInteger == null || voFieldInteger == 0) {
                        continue;
                    }
                    switch (autoQuery.condition()) {
                        case MIN:
                            list.add(criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName).as(Integer.class), voFieldInteger));
                            break;
                        case MAX:
                            list.add(criteriaBuilder.lessThanOrEqualTo(root.get(fieldName).as(Integer.class), voFieldInteger));
                            break;
                        case MATCH:
                            list.add(criteriaBuilder.equal(root.get(fieldName).as(Integer.class), voFieldInteger));
                            break;
                        default:
                            return null;
                    }
                    continue;
                }

                return null;
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
