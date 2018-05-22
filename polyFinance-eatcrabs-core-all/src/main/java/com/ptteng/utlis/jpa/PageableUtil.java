package com.ptteng.utlis.jpa;

import com.ptteng.vo.common.BaseQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;

/**
 * JPA分页工具类
 */
public class PageableUtil {
    public static PageRequest getPageRequest(BaseQuery query) {
        Field[] fields = query.getClass().getDeclaredFields();
        Sort.Direction direction;
        String fieldName;
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoPageable.class)) {
                AutoPageable autoPageable = field.getAnnotation(AutoPageable.class);
                direction = autoPageable.direction();
                fieldName = autoPageable.fieldName().equals("") ? field.getName() : autoPageable.fieldName();
                return new PageRequest(query.getPage() - 1, query.getSize(), direction, fieldName);
            }
        }
        return new PageRequest(query.getPage() - 1, query.getSize());
    }
}
