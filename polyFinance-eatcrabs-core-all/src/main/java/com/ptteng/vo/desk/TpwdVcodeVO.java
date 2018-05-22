package com.ptteng.vo.desk;

import com.ptteng.utlis.validator.VoGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TpwdVcodeVO implements Serializable {
    @NotNull(message = "交易密码不能为空",groups = VoGroup.class)
    @Length(min = 6, max = 6, message = "非法交易密码", groups = VoGroup.class)
    private String password; //交易密码 6位纯数字

    @NotNull(message = "验证码不能为空", groups = VoGroup.class)
    @Length(min = 6, max = 6, message = "非法的验证码", groups = VoGroup.class)
    private String vcode; //验证码

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    @Override
    public String toString() {
        return "TpwdVcodeVO{" +
                "password='" + password + '\'' +
                ", vcode='" + vcode + '\'' +
                '}';
    }
}
