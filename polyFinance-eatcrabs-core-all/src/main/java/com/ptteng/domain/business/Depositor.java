package com.ptteng.domain.business;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "b_depositor", indexes = {
        @Index(name = "uk_user_id", columnList = "user_id",unique = true)})//用户账户信息表
public class Depositor implements Serializable {

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
    private Long userId; //用户的id

    /**
     * 所持有本息之和
     */
    @Column(name = "total", nullable = false)
    private Long total;

    /**
     * 已退出收益之和
     */
    @Column(name = "refund", nullable = false)
    private Long refund;

    /**
     * 交易密码（md5加密后）
     */
    @Column(name = "confirm", columnDefinition = "char(32)", nullable = false)
    private String confirm;


    @Column(name = "create_at", nullable = false)
    private Long createAt;  //创建时间

    @Column(name = "update_at", nullable = false)
    private Long updateAt; //更新时间

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTotal() {
        return total;
    }

    public Long getRefund() {
        return refund;
    }

    public String getConfirm() {
        return confirm;
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

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setRefund(Long refund) {
        this.refund = refund;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Depositor{" +
                "id=" + id +
                ", userId=" + userId +
                ", total=" + total +
                ", refund=" + refund +
                ", confirm='" + confirm + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
