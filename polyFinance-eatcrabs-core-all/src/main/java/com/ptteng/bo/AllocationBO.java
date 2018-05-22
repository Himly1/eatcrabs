package com.ptteng.bo;

import java.io.Serializable;
/**
 * 某产品已分配情况的业务对象
 */
public class AllocationBO implements Serializable {
    /**
     * 已分配金额
     */
    private Long assigned;
    /**
     * 未分配金额
     */
    private Long unassigned;

    @Override
    public String toString() {
        return "AllocationBO{" +
                "assigned=" + assigned +
                ", unassigned=" + unassigned +
                '}';
    }

    public Long getAssigned() {
        return assigned;
    }

    public void setAssigned(Long assigned) {
        this.assigned = assigned;
    }

    public Long getUnassigned() {
        return unassigned;
    }

    public void setUnassigned(Long unassigned) {
        this.unassigned = unassigned;
    }
}
