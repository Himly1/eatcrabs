package com.ptteng;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.internal.org.apache.commons.codec.digest.DigestUtils;
import com.ptteng.bo.PageHandleBO;
import com.ptteng.domain.business.Banner;
import com.ptteng.domain.business.Product;
import com.ptteng.dubbo.BackstageConsumer;
import com.ptteng.utlis.Md5Util;
import com.ptteng.utlis.MoneyUtil;
import com.ptteng.vo.backstage.ProductsQuery;
import com.ptteng.vo.backstage.RoleVO;
import com.ptteng.vo.common.BannersQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashSet;
import java.util.Set;

public class MyTest {
    @Test
    public void test() {
        System.out.println(DigestUtils.sha512Hex("abc000"));
        System.out.println(DigestUtils.sha512Hex("abc123"));
        System.out.println(DigestUtils.sha512Hex("abc666"));
        System.out.println(DigestUtils.sha512Hex("abc888"));
        System.out.println(DigestUtils.sha512Hex("abc999"));
    }

    @Test
    public void test4(){
        System.out.println(Md5Util.getMd5("abc123"));
    }

    @Test
    public void Money() {
/*        long start = 1512057600000L;
        long finish = System.currentTimeMillis();
        int rate = 100;
        long amount = 1000000L;
        long total = new Double(((double) finish - start) / MoneyUtil.YEAR_TO_SECOND * rate * amount / 10000).longValue();
        System.out.println(total);
        System.out.println(MoneyUtil.getAllocation(amount,rate,start,finish));*/
    }
}
