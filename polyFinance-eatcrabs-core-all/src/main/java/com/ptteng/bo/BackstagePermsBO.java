package com.ptteng.bo;

import com.ptteng.domain.manager.Module;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 后台权限信息BO对象
 */
public class BackstagePermsBO implements Serializable {
    private String roleName;
    private List<Module> perms;

    @Override
    public String toString() {
        return "BackstagePermsBO{" +
                "roleName='" + roleName + '\'' +
                ", perms=" + perms +
                '}';
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Module> getPerms() {
        return perms;
    }

    public void setPerms(List<Module> perms) {
        this.perms = perms;
    }
}
