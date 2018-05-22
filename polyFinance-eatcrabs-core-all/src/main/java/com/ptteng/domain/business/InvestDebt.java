package com.ptteng.domain.business;

import com.ptteng.utlis.validator.VoGroup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;


@Entity
@Table(name = "r_invest_debt",
        uniqueConstraints = @UniqueConstraint(name = "uk_invest_id_debt_id",columnNames = {"debt_id", "invest_id"}))//债权匹配关系表
public class InvestDebt implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 投资id
     */
    @Column(name = "invest_id", nullable = false)
    @NotNull(groups = VoGroup.class, message = "投资id非空")
    private Long investId;

    /**
     * 债权id
     */
    @Column(name = "debt_id", nullable = false)
    @NotNull(groups = VoGroup.class, message = "债权id非法")
    private Long debtId;

    @Column(name = "create_at", nullable = false)
    @Null(groups = VoGroup.class, message = "创建时间非法")
    private Long createAt; //创建时间

    public Long getId() {
        return id;
    }

    public Long getInvestId() {
        return investId;
    }

    public Long getDebtId() {
        return debtId;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInvestId(Long investId) {
        this.investId = investId;
    }

    public void setDebtId(Long debtId) {
        this.debtId = debtId;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "InvestDebt{" +
                "id=" + id +
                ", investId=" + investId +
                ", debtId=" + debtId +
                ", createAt=" + createAt +
                '}';
    }
}
