package com.ptteng.vo.desk;

import com.ptteng.utlis.validator.VoGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class MobileVcodeVO implements Serializable {
    @NotNull(message = "手机号不能为空",groups = VoGroup.class)
    @Pattern(regexp = "^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\\d{8})$"
            , message = "非法的手机号",groups = VoGroup.class)
    private String mobile; //手机号

    @NotNull(message = "验证码不能为空", groups = VoGroup.class)
    @Length(min = 6, max = 6, message = "非法的验证码", groups = VoGroup.class)
    private String vcode; //验证码

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    @Override
    public String toString() {
        return "MobileVcodeVO{" +
                "mobile='" + mobile + '\'' +
                ", vcode='" + vcode + '\'' +
                '}';
    }
}
