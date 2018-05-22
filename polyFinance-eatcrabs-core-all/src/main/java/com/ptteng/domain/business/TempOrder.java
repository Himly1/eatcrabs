package com.ptteng.domain.business;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "temp_order", indexes = {
        @Index(name = "idx_deal_number", columnList = "deal_number",unique = true)})//临时订单表
public class TempOrder {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * 交易流水
     */
    @Column(name = "deal_number", columnDefinition = "char(16)", nullable = false)
    private String dealNumber;

    /**
     * 产品id
     */
    @Column(name = "product_id", nullable = false)
    private Long productId;

    /**
     * 交易额
     */
    @Column(name = "amount", nullable = false)
    private Integer amount;

    /**
     * 电子合同图片url
     */
    @Column(name = "img", columnDefinition = "varchar(150)")
    private String img;

    @Column(name = "create_at", nullable = false)
    private Long createAt; //创建时间

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDealNumber() {
        return dealNumber;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getImg() {
        return img;
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

    public void setDealNumber(String dealNumber) {
        this.dealNumber = dealNumber;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "TempOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", dealNumber='" + dealNumber + '\'' +
                ", productId=" + productId +
                ", amount=" + amount +
                ", img='" + img + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
