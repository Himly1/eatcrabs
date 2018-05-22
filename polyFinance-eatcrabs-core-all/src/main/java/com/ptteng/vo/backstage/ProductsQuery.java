package com.ptteng.vo.backstage;

import com.ptteng.utlis.jpa.AutoPageable;
import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;

import java.io.Serializable;

public class ProductsQuery extends BaseQuery implements Serializable {
    @AutoPageable(fieldName = "updateAt")
    @AutoQuery
    private String number;
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE, fieldName = "name")
    private String name;
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "rate")
    private Integer minRate;
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "rate")
    private Integer maxRate;
    @AutoQuery
    private Integer type;
    @AutoQuery(fieldName = "onsale")
    private Integer onSale;
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "term")
    private Long minTerm;
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "term")
    private Long maxTerm;

    @Override
    public String toString() {
        return "ProductsQuery{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", minRate=" + minRate +
                ", maxRate=" + maxRate +
                ", type=" + type +
                ", onSale=" + onSale +
                ", minTerm=" + minTerm +
                ", maxTerm=" + maxTerm +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOnSale() {
        return onSale;
    }

    public void setOnSale(Integer onSale) {
        this.onSale = onSale;
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
}
