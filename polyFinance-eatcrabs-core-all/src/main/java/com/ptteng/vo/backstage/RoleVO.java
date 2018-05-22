package com.ptteng.vo.backstage;

import com.ptteng.utlis.validator.VoGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

public class RoleVO implements Serializable {
    @NotNull(message = "角色名不能为空",groups = VoGroup.class)
    @Pattern(regexp = "^([a-zA-Z0-9_\\u4e00-\\u9fa5]){2,16}$", message = "非法的角色名",groups = VoGroup.class)
    private String roleName; //角色名 6-16 数字 字母（大小写） 中文
    @NotNull(message = "模块id不能为空",groups = VoGroup.class)
    private Set<Long> moduleIds; //模块id集合

    @Override
    public String toString() {
        return "RoleVO{" +
                "roleName='" + roleName + '\'' +
                ", moduleIds=" + moduleIds +
                '}';
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Long> getModuleIds() {
        return moduleIds;
    }

    public void setModuleIds(Set<Long> moduleIds) {
        this.moduleIds = moduleIds;
    }
}
