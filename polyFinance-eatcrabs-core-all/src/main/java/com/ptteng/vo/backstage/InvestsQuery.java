package com.ptteng.vo.backstage;

import com.ptteng.utlis.jpa.AutoPageable;
import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class InvestsQuery extends BaseQuery implements Serializable {
    @AutoPageable(direction = Sort.Direction.ASC,fieldName = "start")
    @AutoQuery
    private String userMobile; //手机号
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE, fieldName = "userName")
    private String userName; //真实姓名
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "start")
    private Long minTime; //投资（起息）起始时间
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "start")
    private Long maxTime; //投资（起息）截止时间
    @AutoQuery
    private String investNumber; //出借合同编号
    @AutoQuery
    private Long productId; //产品id(调用产品列表接口) 全部：0

    private Integer type; //投资状态 -2:已退出 -1:退出中 0：全部 1：投资中
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "finish")
    private Long minFinish;
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "finish")
    private Long maxFinish;

    @Override
    public String toString() {
        return "InvestsQuery{" +
                "userMobile='" + userMobile + '\'' +
                ", userName='" + userName + '\'' +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                ", investNumber='" + investNumber + '\'' +
                ", productId=" + productId +
                ", type=" + type +
                ", minFinish=" + minFinish +
                ", maxFinish=" + maxFinish +
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

    public String getInvestNumber() {
        return investNumber;
    }

    public void setInvestNumber(String investNumber) {
        this.investNumber = investNumber;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
