package com.ptteng.vo.backstage;

import com.ptteng.utlis.jpa.AutoQuery;
import com.ptteng.vo.common.BaseQuery;

import java.io.Serializable;

public class StaffsQuery extends BaseQuery implements Serializable {

    @AutoQuery
    private Long roleId;
    @AutoQuery(condition = AutoQuery.ConditionType.LIKE,fieldName = "account")
    private String staffName;

    @Override
    public String toString() {
        return "StaffsQuery{" +
                "roleId=" + roleId +
                ", staffName='" + staffName + '\'' +
                '}';
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
}
