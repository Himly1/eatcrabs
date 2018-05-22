package com.ptteng.domain.business;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "b_identity", indexes = {
        @Index(name = "uk_id_card", columnList = "id_card",unique = true),
        @Index(name = "uk_user_id", columnList = "user_id",unique = true)})//用户实名信息表
public class Identity implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户的id
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 用户真实姓名
     */
    @Column(name = "name", columnDefinition = "char(5)", nullable = false)
    private String name;

    /**
     * 用户身份证
     */
    @Column(name = "id_card", columnDefinition = "char(18)", nullable = false)
    private String idCard;

    /**
     * 创建时间
     */
    @Column(name = "create_at", nullable = false)
    private Long createAt;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getIdCard() {
        return idCard;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
