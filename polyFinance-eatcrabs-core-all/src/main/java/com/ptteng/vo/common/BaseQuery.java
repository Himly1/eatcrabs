package com.ptteng.vo.common;

import java.io.Serializable;

/**
 * 分页查询基本类
 */
public class BaseQuery implements Serializable {
    private int size = 10;
    private int page = 1;

    @Override
    public String toString() {
        return "BasePageVO{" +
                "size=" + size +
                ", page=" + page +
                '}';
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
