package com.ptteng.domain.manager;

import com.ptteng.utlis.validator.VoGroup;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.io.Serializable;

@Entity//是指这个类映射有数据库表 javax.persistence.Entity 才可以移植
@Table(name = "s_role", indexes = {
        @Index(name = "uk_name", columnList = "name",unique = true)})
public class Role implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名
     */
    @Column(name = "name", columnDefinition = "char(16)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "角色名非空")
    @Pattern(regexp = "^([a-zA-Z0-9_\\u4e00-\\u9fa5]){2,16}$", message = "非法的角色名",groups = VoGroup.class)
    private String name;

    @Column(name = "create_at", nullable = false)
    @Null(groups = VoGroup.class, message = "创建时间非法")
    private Long createAt; //创建时间

    @Column(name = "create_by", columnDefinition = "char(16)", nullable = false)
    @Null(groups = VoGroup.class, message = "创建人非法")
    private String createBy; //创建人

    @Column(name = "update_at", nullable = false)
    @Null(groups = VoGroup.class, message = "更新时间非法")
    private Long updateAt; //更新时间

    @Column(name = "update_by", columnDefinition = "char(16)", nullable = false)
    @Null(groups = VoGroup.class, message = "更新人非法")
    private String updateBy; //更新人

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createAt=" + createAt +
                ", createBy='" + createBy + '\'' +
                ", updateAt=" + updateAt +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}

