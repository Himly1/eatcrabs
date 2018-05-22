package com.ptteng.vo.backstage;

import com.ptteng.utlis.validator.VoGroup;

import javax.validation.constraints.*;
import java.io.Serializable;

public class MobileVO implements Serializable {
    @NotNull(message = "手机号不能为空",groups = VoGroup.class)
    @Pattern(regexp = "^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\\d{8})$"
            , message = "非法的手机号",groups = VoGroup.class)
    private String mobile; //手机号
    @NotNull(message = "用户ID不能为空",groups = VoGroup.class)
    @Min(value = 1, message = "非法的用户ID",groups = VoGroup.class)
    private Long id; //用户id

    @Override
    public String toString() {
        return "MobileVO{" +
                "mobile='" + mobile + '\'' +
                ", id=" + id +
                '}';
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
