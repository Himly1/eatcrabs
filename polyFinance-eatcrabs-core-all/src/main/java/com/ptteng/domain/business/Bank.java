package com.ptteng.domain.business;

import com.ptteng.utlis.validator.VoGroup;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "b_bank", indexes = {
        @Index(name = "uk_name", columnList = "name",unique = true),
        @Index(name = "uk_number", columnList = "number",unique = true)})//银行信息表
public class Bank implements Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 银行名称
     */
    @Column(name = "name", columnDefinition = "char(10)", nullable = false)
    @NotNull(groups = VoGroup.class ,message = "银行名称非空")
    @Size(min = 1, max = 10, message = "非法的银行名称",groups = VoGroup.class)
    private String name;

    /**
     * 机构号
     */
    @Column(name = "number", columnDefinition = "char(10)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "机构号非空")
    @Size(min = 1, max = 10, message = "非法的机构号",groups = VoGroup.class)
    private String number;

    /**
     * 单笔限额
     */
    @Column(name = "single", nullable = false)
    @NotNull(groups = VoGroup.class, message = "单笔限额非空")
    @Range(min = 1, max = 999999999, message = "单笔限额",groups = VoGroup.class)
    private Long single;

    /**
     * 单日限额
     */
    @Column(name = "one_day", nullable = false)
    @NotNull(groups = VoGroup.class, message = "单日限额非空")
    @Range(min = 1, max = 999999999, message = "单日限额",groups = VoGroup.class)
    private Long oneDay;

    /**
     * 图标URL
     */
    @Column(name = "icon", columnDefinition = "varchar(150)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "图标URL非空")
    @Size(min = 20,max = 145,message = "非法的URL",groups = VoGroup.class)
    private String icon;

    @Column(name = "create_at", nullable = false)
    @Null(groups = VoGroup.class, message = "创建时间非法")
    private Long createAt; //创建时间

    @Column(name = "update_at", nullable = false)
    @Null(groups = VoGroup.class, message = "修改时间非法")
    private Long updateAt; //修改时间

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Long getSingle() {
        return single;
    }

    public Long getOneDay() {
        return oneDay;
    }

    public String getIcon() {
        return icon;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSingle(Long single) {
        this.single = single;
    }

    public void setOneDay(Long oneDay) {
        this.oneDay = oneDay;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", single=" + single +
                ", oneDay=" + oneDay +
                ", icon='" + icon + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
