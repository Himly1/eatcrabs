package com.ptteng.bo;

import com.ptteng.domain.business.Invest;

import java.io.Serializable;

/**
 * 某一笔投资及其分配信息
 */
public class UserInvestBO implements Serializable {
    private Invest invest;
    private AllocationBO allocationBO;

    @Override
    public String toString() {
        return "UserInvestBO{" +
                "invest=" + invest +
                ", allocationBO=" + allocationBO +
                '}';
    }

    public Invest getInvest() {
        return invest;
    }

    public void setInvest(Invest invest) {
        this.invest = invest;
    }

    public AllocationBO getAllocationBO() {
        return allocationBO;
    }

    public void setAllocationBO(AllocationBO allocationBO) {
        this.allocationBO = allocationBO;
    }
}
