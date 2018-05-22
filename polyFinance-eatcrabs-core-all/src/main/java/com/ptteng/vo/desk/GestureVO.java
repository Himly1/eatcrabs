package com.ptteng.vo.desk;

import com.ptteng.utlis.validator.VoGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class GestureVO implements Serializable {
    @NotNull(message = "手势密码不能为空",groups = VoGroup.class)
    @Pattern(regexp = "^\\d{4,9}$", message = "手势密码应该为4~9位数字字符串",groups = VoGroup.class)
    private String password;

    @Override
    public String toString() {
        return "GestureVO{" +
                "password='" + password + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
