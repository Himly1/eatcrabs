package com.ptteng.domain.business;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "s_user", indexes = {
        @Index(name = "uk_account", columnList = "account",unique = true),
        @Index(name = "uk_number", columnList = "number",unique = true)}) //用户系统表
public class User implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户登录名（注册手机号）
     */
    @Column(name = "account", columnDefinition = "char(11)", nullable = false)
    private String account;

    /**
     * 登录密码（md5）
     */
    @Column(name = "user_key", columnDefinition = "char(32)", nullable = false)
    private String key;

    /**
     * 手势密码（md5）
     */
    @Column(name = "gesture", columnDefinition = "char(32)", nullable = false)
    private String gesture;

    /**
     * 用户编号 唯一索引 YH开头日期+四位数自增序号(注意同步锁)
     */
    @Column(name = "number", columnDefinition = "char(16)", nullable = false)
    private String number;

    /**
     * 是否冻结 -1:冻结 1：正常
     */
    @Column(name = "freeze", nullable = false)
    private Integer freeze;


    @Column(name = "create_at", nullable = false)
    private Long createAt; //创建时间

    @Column(name = "update_at", nullable = false)
    private Long updateAt; //到期时间

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getKey() {
        return key;
    }

    public String getGesture() {
        return gesture;
    }

    public String getNumber() {
        return number;
    }

    public Integer getFreeze() {
        return freeze;
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

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", key='" + key + '\'' +
                ", gesture='" + gesture + '\'' +
                ", number='" + number + '\'' +
                ", freeze=" + freeze +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
