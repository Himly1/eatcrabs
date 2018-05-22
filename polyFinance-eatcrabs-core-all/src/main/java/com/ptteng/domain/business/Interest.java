package com.ptteng.domain.business;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "b_interest", indexes = {
        @Index(name = "idx_finish", columnList = "finish"),
        @Index(name = "idx_repay", columnList = "repay"),
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_next_refund", columnList = "next_refund")})//利息表
public class Interest implements Serializable {
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
     * 该笔投资金额
     */
    @Column(name = "amount", nullable = false)
    private Long amount;

    /**
     * 年化利率
     */
    @Column(name = "rate", nullable = false)
    private Integer rate;

    /**
     * 返息类型 1：一次到位 2：每月
     */
    @Column(name = "repay", nullable = false)
    private Integer repay;

    /**
     * 开始日期
     */
    @Column(name = "start", nullable = false)
    private Long start;

    /**
     * 最近一次还款日期
     */
    @Column(name = "last_refund", nullable = false)
    private Long lastRefund;

    /**
     * 下次还款日期
     */
    @Column(name = "next_refund", nullable = false)
    private Long nextRefund;

    /**
     * 结束日期
     */
    @Column(name = "finish", nullable = false)
    private Long finish;

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

    public Long getAmount() {
        return amount;
    }

    public Integer getRate() {
        return rate;
    }

    public Integer getRepay() {
        return repay;
    }

    public Long getStart() {
        return start;
    }

    public Long getLastRefund() {
        return lastRefund;
    }

    public Long getNextRefund() {
        return nextRefund;
    }

    public Long getFinish() {
        return finish;
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

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public void setRepay(Integer repay) {
        this.repay = repay;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public void setLastRefund(Long lastRefund) {
        this.lastRefund = lastRefund;
    }

    public void setNextRefund(Long nextRefund) {
        this.nextRefund = nextRefund;
    }

    public void setFinish(Long finish) {
        this.finish = finish;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Interest{" +
                "id=" + id +
                ", userId=" + userId +
                ", amount=" + amount +
                ", rate=" + rate +
                ", repay=" + repay +
                ", start=" + start +
                ", lastRefund=" + lastRefund +
                ", nextRefund=" + nextRefund +
                ", finish=" + finish +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
