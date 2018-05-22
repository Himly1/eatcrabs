package com.ptteng.vo.backstage;

import com.ptteng.utlis.jpa.AutoPageable;
import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;

import java.io.Serializable;

public class MatchesQuery extends BaseQuery implements Serializable {
    @AutoPageable(fieldName = "createAt")
    @AutoQuery
    private Long debtId;
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE, fieldName = "userName")
    private String userName;
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE, fieldName = "productName")
    private String productName;
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "start")
    private Long minStart;
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "start")
    private Long maxStart;
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "finish")
    private Long minFinish;
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "finish")
    private Long maxFinish;

    @Override
    public String toString() {
        return "MatchesQuery{" +
                "debtId=" + debtId +
                ", userName='" + userName + '\'' +
                ", productName='" + productName + '\'' +
                ", minStart=" + minStart +
                ", maxStart=" + maxStart +
                ", minFinish=" + minFinish +
                ", maxFinish=" + maxFinish +
                '}';
    }

    public Long getDebtId() {
        return debtId;
    }

    public void setDebtId(Long debtId) {
        this.debtId = debtId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
