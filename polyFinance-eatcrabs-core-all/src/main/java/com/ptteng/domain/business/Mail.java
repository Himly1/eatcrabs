package com.ptteng.domain.business;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "b_mail") //用户消息列表
public class Mail implements Serializable {
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
     * 标题
     */
    @Column(name = "title", columnDefinition = "varchar(16)", nullable = false)
    private String title;

    /**
     * 详细消息
     */
    @Column(name = "content", columnDefinition = "varchar(100)", nullable = false)
    private String content;

    /**
     * 消息图片url
     */
    @Column(name = "img", columnDefinition = "varchar(150)")
    private String img;

    /**
     * 是否已读 -1：未读 1：已读
     */
    @Column(name = "noted", nullable = false)
    private Integer read;

    @Column(name = "create_at", nullable = false)
    private Long createAt; //创建时间

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImg() {
        return img;
    }

    public Integer getRead() {
        return read;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                ", read=" + read +
                ", createAt=" + createAt +
                '}';
    }
}
