package com.ptteng.vo.backstage;

import com.ptteng.utlis.validator.VoGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class NewKeyVO implements Serializable {

    @NotNull(message = "旧密码不能为空",groups = VoGroup.class)
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", message = "旧密码格式错误",groups = VoGroup.class)
    private String old; //旧密码 6到16位 字母与数字的组合
    @NotNull(message = "新密码不能为空",groups = VoGroup.class)
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", message = "新密码格式错误",groups = VoGroup.class)
    private String newKey; //新密码 6到16位 字母与数字的组合

    @Override
    public String toString() {
        return "NewKeyVO{" +
                ", old='" + old + '\'' +
                ", newKey='" + newKey + '\'' +
                '}';
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String getNewKey() {
        return newKey;
    }

    public void setNewKey(String newKey) {
        this.newKey = newKey;
    }
}
