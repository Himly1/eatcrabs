package com.ptteng.domain.business;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "b_invest", indexes = {
        @Index(name = "idx_invest_number", columnList = "invest_number"),
        @Index(name = "idx_debt_id", columnList = "debt_id")}) //已完成订单（投资）信息表
public class Invest implements Serializable {
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
     * 出借合同编号
     */
    @Column(name = "invest_number", columnDefinition = "char(16)", nullable = false)
    private String investNumber;

    /**
     * 产品的名称
     */
    @Column(name = "product_name", columnDefinition = "char(10)", nullable = false)
    private String productName;

    /**
     * 产品的名称
     */
    @Column(name = "product_id", columnDefinition = "char(10)", nullable = false)
    private String productId;

    /**
     * 投资额度
     */
    @Column(name = "amount", nullable = false)
    private Long amount;

    /**
     * 年化利率
     */
    @Column(name = "rate", nullable = false)
    private Integer rate;

    /**
     * 出借合同图片的url
     */
    @Column(name = "img", columnDefinition = "varchar(150)")
    private String img;

    /**
     * 起息时间(新投资日期)
     */
    @Column(name = "start", nullable = false)
    private Long start;

    /**
     * 退出时间（新到期日期）
     */
    @Column(name = "finish", nullable = false)
    private Long finish;

    /**
     * 富友订单号
     */
    @Column(name = "fuiou", columnDefinition = "varchar(30)", nullable = false)
    private String fuiou;

    /**
     * 债权协议编号
     */
    @Column(name = "claim_number", columnDefinition = "char(16)", nullable = false)
    private String claimNumber;

    /**
     * 债务的id 未匹配的时候：-1L
     */
    @Column(name = "debt_id", nullable = false)
    private Long debtId;

    /**
     * 下单时投资者注册手机号
     */
    @Column(name = "user_mobile", columnDefinition = "char(11)", nullable = false)
    private String userMobile;

    /**
     * 下单时投资者真实姓名
     */
    @Column(name = "user_name", columnDefinition = "char(5)", nullable = false)
    private String userName;

    @Column(name = "create_at", nullable = false)
    private Long createAt; //创建时间

    @Column(name = "update_at", nullable = false)
    private Long updateAt; //创建时间

    @Override
    public String toString() {
        return "Invest{" +
                "id=" + id +
                ", userId=" + userId +
                ", investNumber='" + investNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", productId='" + productId + '\'' +
                ", amount=" + amount +
                ", rate=" + rate +
                ", img='" + img + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", fuiou='" + fuiou + '\'' +
                ", claimNumber='" + claimNumber + '\'' +
                ", debtId=" + debtId +
                ", userMobile='" + userMobile + '\'' +
                ", userName='" + userName + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
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

    public String getInvestNumber() {
        return investNumber;
    }

    public void setInvestNumber(String investNumber) {
        this.investNumber = investNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getFinish() {
        return finish;
    }

    public void setFinish(Long finish) {
        this.finish = finish;
    }

    public String getFuiou() {
        return fuiou;
    }

    public void setFuiou(String fuiou) {
        this.fuiou = fuiou;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public Long getDebtId() {
        return debtId;
    }

    public void setDebtId(Long debtId) {
        this.debtId = debtId;
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

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }
}
