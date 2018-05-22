package com.ptteng.vo.backstage;

import com.ptteng.utlis.jpa.AutoPageable;
import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;

import java.io.Serializable;

public class OpinionsQuery extends BaseQuery implements Serializable {
    @AutoPageable(fieldName = "createAt")
    @AutoQuery
    private String account;
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE, fieldName = "name")
    private String name;
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "createAt")
    private Long minTime;
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "createAt")
    private Long maxTime;
    @AutoQuery
    private String number;

    @Override
    public String toString() {
        return "OpinionsQuery{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                ", number='" + number + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMinTime() {
        return minTime;
    }

    public void setMinTime(Long minTime) {
        this.minTime = minTime;
    }

    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
