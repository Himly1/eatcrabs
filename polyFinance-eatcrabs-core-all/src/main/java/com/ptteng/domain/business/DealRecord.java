package com.ptteng.domain.business;


import com.ptteng.utlis.validator.VoGroup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "b_deal_record") //交易信息记录表
public class DealRecord implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //主键

    /**
     * 用户id
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 用户注册手机号
     */
    @Column(name = "user_mobile", columnDefinition = "char(11)", nullable = false)
    private String userMobile;

    /**
     * 用户姓名
     */
    @Column(name = "user_name", columnDefinition = "char(5)", nullable = false)
    private String userName; //用户姓名

    /**
     * 交易流水
     */
    @Column(name = "number", columnDefinition = "char(16)", nullable = false)
    private String number; //交易流水

    /**
     * 产品ID
     */
    @Column(name = "product_id", nullable = false)
    private Long productId; //产品id

    /**
     * 产品名称
     */
    @Column(name = "product_name", columnDefinition = "char(10)", nullable = false)
    private String productName;

    /**
     * 产品编号
     */
    @Column(name = "product_number", columnDefinition = "char(15)", nullable = false)
    private String productNumber;

    /**
     * 交易类型 -1:回款 1:付款
     */
    @Column(name = "pay", nullable = false)
    private Integer pay;

    /**
     * 交易金额 单位：分
     */
    @Column(name = "amount", nullable = false)
    private Long amount;

    /**
     * 是否成功 -1:失败 1:成功
     */
    @Column(name = "success", nullable = false)
    private Integer success;

    /**
     * 交易方式 工商银行（1234）
     */
    @Column(name = "deal_by", columnDefinition = "char(15)", nullable = false)
    private String dealBy;

    @Column(name = "create_at", nullable = false)
    private Long createAt; //创建时间

    @Override
    public String toString() {
        return "DealRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", userMobile='" + userMobile + '\'' +
                ", userName='" + userName + '\'' +
                ", number='" + number + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productNumber='" + productNumber + '\'' +
                ", pay=" + pay +
                ", amount=" + amount +
                ", success=" + success +
                ", dealBy='" + dealBy + '\'' +
                ", createAt=" + createAt +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getDealBy() {
        return dealBy;
    }

    public void setDealBy(String dealBy) {
        this.dealBy = dealBy;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }
}