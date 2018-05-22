package com.ptteng.vo.backstage;

import com.ptteng.utlis.validator.VoGroup;

import javax.validation.constraints.*;
import java.io.Serializable;

public class FreezeVO implements Serializable {
    @NotNull(message = "用户ID不能为空",groups = VoGroup.class)
    private Long id; //用户id
    @NotNull(message = "非法的状态",groups = VoGroup.class)
    private Integer freeze; //账户冻结状态

    @Override
    public String toString() {
        return "FreezeVO{" +
                "id=" + id +
                ", freeze=" + freeze +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }
}
