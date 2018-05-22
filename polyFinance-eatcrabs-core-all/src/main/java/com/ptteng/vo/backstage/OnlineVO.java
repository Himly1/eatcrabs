package com.ptteng.vo.backstage;

import com.ptteng.utlis.validator.VoGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class OnlineVO implements Serializable {
    /**
     * banner图id
     */
    @NotNull(groups = VoGroup.class,message = "id不能为空")
    private Long id;

    /**
     * 1:上线 -1下线
     */
    @NotNull(groups = VoGroup.class,message = "上下线不能为空")
    private Integer online;

    @Override
    public String toString() {
        return "OnlineVO{" +
                "id=" + id +
                ", online=" + online +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }
}
