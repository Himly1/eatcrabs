package com.ptteng.vo.backstage;

import com.ptteng.utlis.jpa.AutoPageable;
import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class RecommendQuery extends BaseQuery implements Serializable {
    @AutoPageable(direction = Sort.Direction.ASC,fieldName = "start")
    @AutoQuery(condition = AutoQuery.ConditionType.MATCH,fieldName = "debtId")
    private Long isMatched; //是否已经被匹配
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "start")
    private Long maxTime; //投资（起息）截止时间
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "amount")
    private Long maxAmount; //投资（起息）截止时间

    @Override
    public String toString() {
        return "RecommendQuery{" +
                "isMatched=" + isMatched +
                ", maxTime=" + maxTime +
                ", maxAmount=" + maxAmount +
                '}';
    }

    public Long getIsMatched() {
        return isMatched;
    }

    public void setIsMatched(Long isMatched) {
        this.isMatched = isMatched;
    }

    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }
}
