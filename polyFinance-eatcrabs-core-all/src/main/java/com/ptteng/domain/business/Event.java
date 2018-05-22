package com.ptteng.domain.business;

import com.ptteng.utlis.validator.VoGroup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "b_event")//活动信息模板表
public class Event implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 活动标题
     */
    @Column(name = "title", columnDefinition = "char(16)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "活动标题非空")
    @Size(min = 1, max = 16, groups = VoGroup.class)
    private String title;

    /**
     * 发送目标 0:所有用户 1:已实名用户 2：已绑卡用户 3：已投资用户
     */
    @Column(name = "aims", nullable = false)
    @NotNull(groups = VoGroup.class, message = "发送目标非空")
    private Integer aims;

    /**
     * 活动消息图片url地址
     */
    @Column(name = "img", columnDefinition = "varchar(150)")
    private String img;

    /**
     * 消息内容
     */
    @Column(name = "content", columnDefinition = "varchar(100)", nullable = false)
    @NotNull(groups = VoGroup.class, message = "消息内容非空")
    @Size(min = 1, max = 100, groups = VoGroup.class, message = "消息内容非法")
    private String content;

    /**
     * 是否上线 -1：草稿 1：上线
     */
    @Column(name = "online", nullable = false)
    @Null(groups = VoGroup.class, message = "上线状态非法")
    private Integer online;

    @Column(name = "create_at", nullable = false)
    @Null(groups = VoGroup.class, message = "创建时间非法")
    private Long createAt; //创建时间

    @Column(name = "update_at", nullable = false)
    @Null(groups = VoGroup.class, message = "更新时间非法")
    private Long updateAt; //更新时间

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getAims() {
        return aims;
    }

    public String getImg() {
        return img;
    }

    public Integer getOnline() {
        return online;
    }

    public String getContent() {
        return content;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAims(Integer aims) {
        this.aims = aims;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", aims=" + aims +
                ", img='" + img + '\'' +
                ", online=" + online +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
