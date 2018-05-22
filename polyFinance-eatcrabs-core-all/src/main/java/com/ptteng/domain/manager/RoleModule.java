package com.ptteng.domain.manager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "r_role_module",
        uniqueConstraints = @UniqueConstraint(name = "uk_role_id_module_id",columnNames = {"role_id", "module_id"}))//角色模块关系表
public class RoleModule implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色id
     */
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    /**
     * 模块id
     */
    @Column(name = "module_id", nullable = false)
    private Long moduleId;

    @Column(name = "create_at", nullable = false)
    private Long createAt; //创建时间

    
    @Column(name = "update_at", nullable = false)
    private Long updateAt; //更新时间

    public Long getId() {
        return id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "RoleModule{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", moduleId=" + moduleId +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
