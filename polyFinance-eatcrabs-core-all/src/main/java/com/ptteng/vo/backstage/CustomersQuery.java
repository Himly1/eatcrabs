package com.ptteng.vo.backstage;

import com.ptteng.utlis.jpa.AutoPageable;
import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;
import java.io.Serializable;

public class CustomersQuery extends BaseQuery implements Serializable {
    @AutoPageable(fieldName = "createAt")
    @AutoQuery
    private String account; //手机号
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE, fieldName = "name")
    private String name; //真实姓名
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "createAt")
    private Long minTime; //起始注册时间
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "createAt")
    private Long maxTime; //终止注册时间
    @AutoQuery
    private String number; //用户编号
    @AutoQuery
    private Integer freeze; //账户冻结状态 值为 1 或 -1

    @Override
    public String toString() {
        return "CustomersQuery{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                ", number='" + number + '\'' +
                ", freeze=" + freeze +
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

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }
}
