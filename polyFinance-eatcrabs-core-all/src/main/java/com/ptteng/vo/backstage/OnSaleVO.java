package com.ptteng.vo.backstage;

import com.ptteng.utlis.validator.VoGroup;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class OnSaleVO implements Serializable {
    @NotNull(message = "用户ID不能为空",groups = VoGroup.class)
    private Long id; //用户id
    @NotNull(message = "非法的状态",groups = VoGroup.class)
    private Integer onSale; //1:下架产品 -1：上架产品

    @Override
    public String toString() {
        return "OnSaleVO{" +
                "id=" + id +
                ", onSale=" + onSale +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOnSale() {
        return onSale;
    }

    public void setOnSale(Integer onSale) {
        this.onSale = onSale;
    }
}
