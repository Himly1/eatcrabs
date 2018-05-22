package com.ptteng.vo.backstage;

import com.ptteng.utlis.jpa.AutoPageable;
import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class BanksQuery extends BaseQuery implements Serializable {

    @AutoPageable(direction = Sort.Direction.DESC,fieldName = "updateAt")
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE,fieldName = "name")
    private String name; //银行名称 0-16位中文
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "single")
    private Long minSingle; //最小单笔限额(分)
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "single")
    private Long maxSingle; //最大单笔限额(分)
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "oneDay")
    private Long minOneDay; //最小日累计限额(元)
    @AutoQuery(condition = AutoQuery.ConditionType.MAX,fieldName = "oneDay")
    private Long maxOneDay; //最大日累计限额(元)

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMinSingle() {
        return minSingle;
    }

    public void setMinSingle(Long minSingle) {
        this.minSingle = minSingle;
    }

    public Long getMaxSingle() {
        return maxSingle;
    }

    public void setMaxSingle(Long maxSingle) {
        this.maxSingle = maxSingle;
    }

    public Long getMinOneDay() {
        return minOneDay;
    }

    public void setMinOneDay(Long minOneDay) {
        this.minOneDay = minOneDay;
    }

    public Long getMaxOneDay() {
        return maxOneDay;
    }

    public void setMaxOneDay(Long maxOneDay) {
        this.maxOneDay = maxOneDay;
    }

    @Override
    public String toString() {
        return "BanksQuery{" +
                "name='" + name + '\'' +
                ", minSingle=" + minSingle +
                ", maxSingle=" + maxSingle +
                ", minOneDay=" + minOneDay +
                ", maxOneDay=" + maxOneDay +
                '}';
    }
}
