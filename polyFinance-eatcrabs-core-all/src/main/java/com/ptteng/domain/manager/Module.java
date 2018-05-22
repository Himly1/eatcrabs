package com.ptteng.domain.manager;

import com.ptteng.utlis.validator.VoGroup;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 模板（权限）信息表
 */
@Entity
@Table(name = "s_module", indexes = {
        @Index(name = "uk_name", columnList = "name",unique = true),
        @Index(name = "uk_url", columnList = "url",unique = true)})
public class Module implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //主键

    /**
     * 模块名称
     */
    @Column(name = "name", columnDefinition = "char(16)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "模块名称非空")
    @Size(min = 1, max = 16,groups = VoGroup.class, message = "模块名称非法")
    private String name;

    /**
     * 父模块id 1：业务；2：运营；3：后台
     */
    @Column(name = "fmodule_id", nullable = false)
    @NotNull(groups = VoGroup.class, message = "父模块id非空")
    @Min(value = 1,groups = VoGroup.class, message = "父模块id非法")
    private Integer fmoduleId;

    /**
     * 接口地址起始部分
     */
    @Column(name = "url", columnDefinition = "char(30)", nullable = false)
    @Null(groups = VoGroup.class, message = "接口地址起始部分非法")
    private String url;

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
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fmoduleId=" + fmoduleId +
                ", url='" + url + '\'' +
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

    public Integer getFmoduleId() {
        return fmoduleId;
    }

    public void setFmoduleId(Integer fmoduleId) {
        this.fmoduleId = fmoduleId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
