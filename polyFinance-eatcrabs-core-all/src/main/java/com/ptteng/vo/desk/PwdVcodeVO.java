package com.ptteng.vo.desk;

import com.ptteng.utlis.validator.VoGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class PwdVcodeVO implements Serializable{
    @NotNull(message = "密码不能为空",groups = VoGroup.class)
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", message = "非法的登录密码",groups = VoGroup.class)
    private String password; //密码 6到16位 字母与数字的组合

    @NotNull(message = "验证码不能为空", groups = VoGroup.class)
    @Length(min = 6, max = 6, message = "非法的验证码", groups = VoGroup.class)
    private String vcode; //验证码
}
