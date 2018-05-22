package com.ptteng.domain.manager;

import com.ptteng.utlis.validator.VoGroup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "s_staff", indexes = {
        @Index(name = "uk_account", columnList = "account", unique = true)}) //员工系统表
public class Staff implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 员工登录名
     */
    @Column(name = "account", columnDefinition = "char(16)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "员工登录名非空")
    @Pattern(regexp = "^([a-zA-Z0-9_\\u4e00-\\u9fa5]){6,16}$", message = "非法的登录名", groups = VoGroup.class)
    private String account;

    /**
     * 密码（md5）
     */
    @Column(name = "user_key", columnDefinition = "char(32)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "密码非空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", message = "密码格式错误",groups = VoGroup.class)
    private String key;

    /**
     * 员工手机号
     */
    @Column(name = "mobile", columnDefinition = "char(11)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "员工手机号非空")
    @Pattern(regexp = "^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\\d{8})$"
            , message = "非法的手机号",groups = VoGroup.class)
    private String mobile;

    /**
     * 角色id
     */
    @Column(name = "role_id", nullable = false)
    @NotNull(groups = VoGroup.class)
    private Long roleId;


    @Column(name = "create_at", nullable = false)
    @Null(groups = VoGroup.class)
    private Long createAt; //创建时间

    @Column(name = "create_by", columnDefinition = "char(16)", nullable = false)
    @Null(groups = VoGroup.class)
    private String createBy; //创建人

    @Column(name = "update_at", nullable = false)
    @Null(groups = VoGroup.class)
    private Long updateAt; //更新时间

    @Column(name = "update_by", columnDefinition = "char(16)", nullable = false)
    @Null(groups = VoGroup.class)
    private String updateBy; //更新人

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", key='" + key + '\'' +
                ", mobile='" + mobile + '\'' +
                ", roleId=" + roleId +
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
