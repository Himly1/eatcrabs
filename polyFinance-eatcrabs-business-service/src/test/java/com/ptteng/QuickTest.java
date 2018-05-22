package com.ptteng;

import com.ptteng.utlis.Md5Util;
import org.junit.Test;

public class QuickTest {
    @Test
    public void time() {
        System.out.println(Md5Util.getMd5("123456"));
    }

    @Test
    public void test() {
        long start = 1526365977084L;
        long finish = 1656365977084L;
        long current = System.currentTimeMillis();
        System.out.println(current > finish ? -1 : current < start ? 1 : 0);
    }
}
