package com.ptteng.domain.business;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "b_user_card", indexes = {
        @Index(name = "uk_bank_card", columnList = "bank_card",unique = true)}) //用户绑卡信息表
public class UserCard implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    /**
     * 绑卡的卡号
     */
    @Column(name = "bank_card", columnDefinition = "char(20)", nullable = false)
    private String bankCard;

    /**
     * 绑卡的银行名
     */
    @Column(name = "bank_name", columnDefinition = "varchar(40)", nullable = false)
    private String bankName;

    /**
     * 绑卡的银行机构号
     */
    @Column(name = "bank_number", columnDefinition = "char(10)", nullable = false)
    private String bankNumber;

    /**
     * 银行预留手机号
     */
    @Column(name = "bank_mobile", columnDefinition = "char(11)", nullable = false)
    private String bankMobile;

    @Column(name = "create_at", nullable = false)
    private Long createAt; //创建时间

    @Column(name = "update_at", nullable = false)
    private Long updateAt; //更新时间

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getBankCard() {
        return bankCard;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public String getBankMobile() {
        return bankMobile;
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public void setBankMobile(String bankMobile) {
        this.bankMobile = bankMobile;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "UserCard{" +
                "id=" + id +
                ", userId=" + userId +
                ", bankCard='" + bankCard + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankNumber='" + bankNumber + '\'' +
                ", bankMobile='" + bankMobile + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
