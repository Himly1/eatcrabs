package com.ptteng.vo.desk;

import com.ptteng.utlis.validator.VoGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class IdentityVO implements Serializable {
    @NotNull(message = "真实姓名不能为空",groups = VoGroup.class)
    @Pattern(regexp = "^([a-zA-Z0-9_\\u4e00-\\u9fa5]){1,16}$", message = "非法真实姓名",groups = VoGroup.class)
    private String uesrName;

    @NotNull(message = "身份证号不能为空",groups = VoGroup.class)
    @Length(min = 15, max = 18, message = "非法身份证号")
    private String idCard;

    public String getUesrName() {
        return uesrName;
    }

    public void setUesrName(String uesrName) {
        this.uesrName = uesrName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "IdentityVO{" +
                "uesrName='" + uesrName + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
