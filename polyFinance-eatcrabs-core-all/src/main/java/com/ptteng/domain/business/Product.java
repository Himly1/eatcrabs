package com.ptteng.domain.business;


import com.ptteng.utlis.validator.VoGroup;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "b_product", indexes = {
        @Index(name = "uk_name", columnList = "name", unique = true),
        @Index(name = "uk_number", columnList = "number", unique = true)}) //产品信息表
public class Product implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 产品名称
     */
    @Column(name = "name", columnDefinition = "char(10)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "产品名称非空")
    @Size(min = 3, max = 10,groups = VoGroup.class, message = "产品名称非法")
    private String name;

    /**
     * 产品编号
     */
    @Column(name = "number", columnDefinition = "char(16)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "产品编号非空")
    @Size(min = 15, max = 16,groups = VoGroup.class, message = "产品编号非法")
    private String number;

    /**
     * 所属模块 0：全部 1：新手计划 2：人气精选 3：精选产品(前台首页)
     */
    @Column(name = "type", nullable = false)
    @NotNull(groups = VoGroup.class, message = "所属模块非空")
    private Integer type;

    /**
     * 年化利率
     */
    @Column(name = "rate", nullable = false)
    @NotNull(groups = VoGroup.class, message = "年化利率非空")
    @Range(min = 100, max = 2000,groups = VoGroup.class, message = "年化利率非法")
    private Integer rate;

    /**
     * 期限 单位为毫秒
     */
    @Column(name = "term", nullable = false)
    @NotNull(groups = VoGroup.class, message = "期限非空")
    private Long term;

    /**
     * 起投金额 新增时无需提供，后台已经锁死为5000
     */
    @Column(name = "least", nullable = false)
    @Null(groups = VoGroup.class, message = "起投金额非法")
    private Integer least;

    /**
     * 追加金额 新增时无需提供，后台已经锁死为1000
     */
    @Column(name = "step", nullable = false)
    @Null(groups = VoGroup.class, message = "追加金额非法")
    private Integer step;

    /**
     * 限额 除了新手计划是5W，其他都是-1（无限制）
     */
    @Column(name = "quota", nullable = false)
    @Null(groups = VoGroup.class, message = "限额非法")
    private Integer quota;

    /**
     * 起息日期 新增时无需提供，后台已经锁死为1;T(自然日)+1)
     */
    @Column(name = "began", columnDefinition = "char(10)", nullable = false)
    @Null(groups = VoGroup.class, message = "起息时间非法")
    private String begin;

    /**
     * 返息方式 还款方式 还款方式 1：一次到位 2：每月
     */
    @Column(name = "repay", nullable = false)
    @NotNull(groups = VoGroup.class, message = "返息方式非空")
    private Integer repay;

    /**
     * 是否正在出售 是否正在出售 -1:停售 1：出售
     */
    @Column(name = "onsale", nullable = false)
    @Null(groups = VoGroup.class, message = "是否正在出售状态非空")
    private Integer onsale;

    /**
     * 项目描述
     */
    @Column(name = "content", columnDefinition = "varchar(100)", nullable = false)
    @NotNull(groups = VoGroup.class)
    @Size(min = 1, max = 90,groups = VoGroup.class, message = "项目描述非空")
    private String content;


    @Column(name = "create_at", nullable = false)
    @Null(groups = VoGroup.class, message = "创建时间非法")
    private Long createAt; //创建时间

    @Column(name = "update_at", nullable = false)
    @Null(groups = VoGroup.class, message = "更新时间非法")
    private Long updateAt; //更新时间

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", type=" + type +
                ", rate=" + rate +
                ", term=" + term +
                ", least=" + least +
                ", step=" + step +
                ", quota=" + quota +
                ", begin='" + begin + '\'' +
                ", repay=" + repay +
                ", onsale=" + onsale +
                ", content='" + content + '\'' +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    public Integer getLeast() {
        return least;
    }

    public void setLeast(Integer least) {
        this.least = least;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public Integer getRepay() {
        return repay;
    }

    public void setRepay(Integer repay) {
        this.repay = repay;
    }

    public Integer getOnsale() {
        return onsale;
    }

    public void setOnsale(Integer onsale) {
        this.onsale = onsale;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
