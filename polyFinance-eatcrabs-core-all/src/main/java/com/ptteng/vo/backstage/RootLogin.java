package com.ptteng.vo.backstage;

import com.ptteng.utlis.validator.VoGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class RootLogin implements Serializable {

    @NotNull(message = "登录名不能为空",groups = VoGroup.class)
    @Pattern(regexp = "^([a-zA-Z0-9_\\u4e00-\\u9fa5]){6,16}$", message = "非法的登录名",groups = VoGroup.class)
    private String rootName; //超管名 6-16 数字 字母（大小写） 中文
    @NotNull(message = "密码不能为空",groups = VoGroup.class)
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", message = "非法的登录密码",groups = VoGroup.class)
    private String password; //密码 6到16位 字母与数字的组合

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRootName() {
        return rootName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Root{" +
                "rootName='" + rootName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
