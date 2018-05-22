package com.ptteng.vo.backstage;

import com.ptteng.utlis.jpa.AutoPageable;
import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;

import java.io.Serializable;

public class DealsQuery extends BaseQuery implements Serializable {
    @AutoPageable(fieldName = "createAt")
    @AutoQuery
    private String userMobile; //手机号
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE, fieldName = "userName")
    private String userName; //真实姓名
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "createAt")
    private Long minTime; //交易起始时间
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "createAt")
    private Long maxTime; //交易截止时间
    @AutoQuery
    private String number; //交易流水号
    @AutoQuery
    private Long pay; //投资状态 -1：回款 0:所有 1：付款
    @AutoQuery
    private Long productId; //产品id
    @AutoQuery
    private Integer success; //交易结果

    @Override
    public String toString() {
        return "DealsQuery{" +
                "userMobile='" + userMobile + '\'' +
                ", userName='" + userName + '\'' +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                ", number='" + number + '\'' +
                ", pay=" + pay +
                ", productId='" + productId + '\'' +
                ", success=" + success +
                '}';
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Long getPay() {
        return pay;
    }

    public void setPay(Long pay) {
        this.pay = pay;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
