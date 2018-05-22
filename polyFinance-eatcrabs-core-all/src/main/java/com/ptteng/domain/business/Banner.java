package com.ptteng.domain.business;

import com.ptteng.utlis.validator.VoGroup;
import org.hibernate.jpa.criteria.expression.function.AggregationFunction;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "b_banner") //banner图信息表
public class Banner implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * banner图名称
     */
    @Column(name = "name", columnDefinition = "char(16)", nullable = false)
    @NotNull(groups = VoGroup.class,message = "banner图名字非空")
    @Size(min = 1, max = 16, message = "banner名称非法",groups = VoGroup.class)
    private String name;

    /**
     * 详细介绍
     */
    @Column(name = "content", columnDefinition = "varchar(100)", nullable = false)
    @NotNull(groups = VoGroup.class)
    @Size(min = 1, max = 100, message = "详细介绍非法",groups = VoGroup.class)
    private String content;

    /**
     * 图片URL
     */
    @Column(name = "img", columnDefinition = "varchar(150)", nullable = false)
    @NotNull(groups = VoGroup.class)
    @Size(min = 20, max = 145, message = "非法的URL",groups = VoGroup.class)
    private String img;

    /**
     * 某产品已分配情况的业务对象
     */
    @Column(name = "online", nullable = false)
    @Null(groups = VoGroup.class, message = "上线状态非法")
    private Integer online; //是否为上线状态 -1:草稿 1：已上线

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

    public String getContent() {
        return content;
    }

    public String getImg() {
        return img;
    }

    public Integer getOnline() {
        return online;
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

    public void setContent(String content) {
        this.content = content;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", online=" + online +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
