package com.ptteng.vo.backstage;

import com.ptteng.utlis.jpa.AutoPageable;
import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;

import java.io.Serializable;

public class DebtsQuery extends BaseQuery implements Serializable {
    @AutoPageable(fieldName = "createAt")
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE, fieldName = "name")
    private String name; //真实姓名
    @AutoQuery
    private String mobile; //手机号
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "start")
    private Long minStart; //借款日期起始
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "start")
    private Long maxStart; //借款日期截止
    @AutoQuery
    private String number; //债权代号
    @AutoQuery
    private String idCard; //身份证号
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "finish")
    private Long minFinish; //最小到期日期
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "finish")
    private Long maxFinish; //最大到期日期
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "term")
    private Long minTerm; //最小还款期限 折算为毫秒数
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "term")
    private Long maxTerm; //最大还款期限 折算为毫秒数
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "rate")
    private Integer minRate; //最小借款年利率
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "rate")
    private Integer maxRate; //最大借款年利率
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "amount")
    private Long minAmount; //最小借款金额 分为单位
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "amount")
    private Long maxAmount; //最大借款金额 分为单位
    private Integer used; //借款状态 -1：未使用 0：全部 1:使用中 2：已过期

    @Override
    public String toString() {
        return "DebtsQuery{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", minStart=" + minStart +
                ", maxStart=" + maxStart +
                ", number='" + number + '\'' +
                ", idCard='" + idCard + '\'' +
                ", minFinish=" + minFinish +
                ", maxFinish=" + maxFinish +
                ", minTerm=" + minTerm +
                ", maxTerm=" + maxTerm +
                ", minRate=" + minRate +
                ", maxRate=" + maxRate +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", used=" + used +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getMinStart() {
        return minStart;
    }

    public void setMinStart(Long minStart) {
        this.minStart = minStart;
    }

    public Long getMaxStart() {
        return maxStart;
    }

    public void setMaxStart(Long maxStart) {
        this.maxStart = maxStart;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Long getMinFinish() {
        return minFinish;
    }

    public void setMinFinish(Long minFinish) {
        this.minFinish = minFinish;
    }

    public Long getMaxFinish() {
        return maxFinish;
    }

    public void setMaxFinish(Long maxFinish) {
        this.maxFinish = maxFinish;
    }

    public Long getMinTerm() {
        return minTerm;
    }

    public void setMinTerm(Long minTerm) {
        this.minTerm = minTerm;
    }

    public Long getMaxTerm() {
        return maxTerm;
    }

    public void setMaxTerm(Long maxTerm) {
        this.maxTerm = maxTerm;
    }

    public Integer getMinRate() {
        return minRate;
    }

    public void setMinRate(Integer minRate) {
        this.minRate = minRate;
    }

    public Integer getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(Integer maxRate) {
        this.maxRate = maxRate;
    }

    public Long getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Long minAmount) {
        this.minAmount = minAmount;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }
}
