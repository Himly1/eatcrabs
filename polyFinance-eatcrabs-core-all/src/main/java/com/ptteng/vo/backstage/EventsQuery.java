package com.ptteng.vo.backstage;

import com.ptteng.utlis.jpa.AutoPageable;
import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;

import java.io.Serializable;

public class EventsQuery extends BaseQuery implements Serializable {
    @AutoPageable(fieldName = "updateAt")
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE, fieldName = "title")
    private String title;
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "updateAt")
    private Long minTime;
    @AutoQuery(condition = AutoQuery.ConditionType.MIN,fieldName = "updateAt")
    private Long maxTime;
    @AutoQuery
    private Integer aims;
    @AutoQuery
    private Integer online;

    @Override
    public String toString() {
        return "TipsQuery{" +
                "title='" + title + '\'' +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                ", aims=" + aims +
                ", online=" + online +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getAims() {
        return aims;
    }

    public void setAims(Integer aims) {
        this.aims = aims;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }
}
