package com.ptteng.bo;

import java.io.Serializable;

/**
 * 净资产+总收益BO对象
 */
public class DepositorInfoBO implements Serializable {
    private Long total;
    private Long income;

    @Override
    public String toString() {
        return "DepositorInfoBO{" +
                "total=" + total +
                ", income=" + income +
                '}';
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }
}
