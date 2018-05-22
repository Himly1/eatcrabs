package com.ptteng.bo;

import com.ptteng.domain.business.User;

import java.io.Serializable;

/**
 * 用户详情页面个人信息
 */
public class CustomerBO implements Serializable {
    /**
     * 包含的user对象信息
     */
    private User user;
    /**
     * 包含的账户收益对象信息
     */
    private DepositorInfoBO depositorInfoBO;
    /**
     * 包含的用户姓名
     */
    private String name;

    @Override
    public String toString() {
        return "CustomerBO{" +
                "user=" + user +
                ", depositorInfoBO=" + depositorInfoBO +
                ", name='" + name + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DepositorInfoBO getDepositorInfoBO() {
        return depositorInfoBO;
    }

    public void setDepositorInfoBO(DepositorInfoBO depositorInfoBO) {
        this.depositorInfoBO = depositorInfoBO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
