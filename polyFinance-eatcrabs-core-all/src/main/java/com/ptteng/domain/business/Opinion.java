package com.ptteng.domain.business;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "b_opinion") //用户意见表
public class Opinion implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户姓名
     */
    @Column(name = "name", columnDefinition = "char(5)", nullable = false)
    private String name;

    /**
     * 用户注册手机号
     */
    @Column(name = "account", columnDefinition = "char(11)", nullable = false)
    private String account;

    /**
     * 详细内容
     */
    @Column(name = "content", columnDefinition = "varchar(100)", nullable = false)
    private String content;

    /**
     * 用户编号
     */
    @Column(name = "number", columnDefinition = "char(16)", nullable = false)
    private String number;

    @Column(name = "create_at", nullable = false)
    private Long createAt; //创建时间

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getContent() {
        return content;
    }

    public String getNumber() {
        return number;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Opinion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", content='" + content + '\'' +
                ", number='" + number + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
