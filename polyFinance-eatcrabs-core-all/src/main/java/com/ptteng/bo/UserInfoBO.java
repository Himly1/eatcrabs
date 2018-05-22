package com.ptteng.bo;

import com.ptteng.domain.business.Identity;
import com.ptteng.domain.business.User;

import java.io.Serializable;

/**
 * 用户以及其实名制信息
 */
public class UserInfoBO implements Serializable {
    private User user;
    private Identity identity;

    @Override
    public String toString() {
        return "UserInfoBO{" +
                ", user=" + user +
                ", identity=" + identity +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
}
