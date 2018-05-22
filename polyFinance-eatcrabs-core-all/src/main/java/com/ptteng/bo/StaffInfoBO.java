package com.ptteng.bo;

import com.ptteng.domain.manager.Staff;

import java.io.Serializable;

/**
 * 员工有关角色信息
 */
public class StaffInfoBO implements Serializable {
    private Staff staff;
    private String roleName;

    @Override
    public String toString() {
        return "StaffInfoBO{" +
                "staff=" + staff +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
