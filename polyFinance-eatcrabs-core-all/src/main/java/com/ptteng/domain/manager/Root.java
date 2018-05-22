package com.ptteng.domain.manager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "s_root", indexes = {
        @Index(name = "uk_account", columnList = "account",unique = true)}) //超管信息表
public class Root implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //主键

    /**
     * 登录名
     */
    @Column(name = "account", columnDefinition = "char(16)", nullable = false)
    private String account;

    /**
     * 超级管理员登录密码（sha512）
     */
    @Column(name = "user_key", columnDefinition = "char(128)", nullable = false)
    private String key;

    @Column(name = "create_at", nullable = false)
    private Long createAt; //创建时间
    @Column(name = "update_at", nullable = false)
    private Long updateAt; //更新时间

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getKey() {
        return key;
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

    public void setAccount(String account) {
        this.account = account;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Root{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", key='" + key + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
