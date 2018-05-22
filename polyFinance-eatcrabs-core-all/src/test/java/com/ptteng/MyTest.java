package com.ptteng;

import com.ptteng.bo.UserInfoBO;
import com.ptteng.utlis.Md5Util;
import com.ptteng.utlis.MockUtils;
import org.junit.Test;

public class MyTest {
    @Test
    public void boMock(){
        System.out.println( MockUtils.getMockBOPage(UserInfoBO.class));
    }

    @Test
    public void md5(){
        System.out.println(Md5Util.getMd5("123456"));
    }
}
