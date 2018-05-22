package com.ptteng.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询BO对象
 */
public class PageHandleBO<T extends Serializable> implements Serializable {

    private Long count;

    private List<T> objects;

    @Override
    public String toString() {
        return "PageHandleBO{" +
                "count=" + count +
                ", objects=" + objects +
                '}';
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }
}
