package com.ptteng.vo.common;

import com.ptteng.utlis.jpa.AutoPageable;
import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class BannersQuery extends BaseQuery implements Serializable {
    @AutoPageable(fieldName = "updateAt")
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "updateAt")
    private Long updateFrom; //最小保存时间
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "updateAt")
    private Long updateTo; //最大保存时间
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE,fieldName = "name")
    private String name; //标题
    @AutoQuery(condition = AutoQuery.ConditionType.MATCH,fieldName = "online")
    private Integer online; //是否为上线状态 -1:草稿 1：已上线

    public Long getUpdateFrom() {
        return updateFrom;
    }

    public void setUpdateFrom(Long updateFrom) {
        this.updateFrom = updateFrom;
    }

    public Long getUpdateTo() {
        return updateTo;
    }

    public void setUpdateTo(Long updateTo) {
        this.updateTo = updateTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "BannersQuery{" +
                "updateFrom=" + updateFrom +
                ", updateTo=" + updateTo +
                ", name='" + name + '\'' +
                ", online=" + online +
                '}';
    }
}
