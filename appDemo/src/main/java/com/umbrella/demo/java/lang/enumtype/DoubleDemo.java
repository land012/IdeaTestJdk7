package com.umbrella.demo.java.lang.enumtype;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by xudazhou on 2015/8/31.
 */
public class DoubleDemo {
    @Test
    public void test1() {
        double d1 = 3d; // 双精度浮点数表示法
        double d2 = 2 * 3;
        long l3 = 5;
        System.out.println(l3 / d2); // 0.8333333333333334
    }

    /**
     * 进行耗时的浮点运算
     * TODO 不好使...
     */
    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        double k = 1d;
        for(int i=0; i<1000000; i++) {
            k += k*Math.E;
        }
        System.out.println((System.currentTimeMillis()-start));
    }

    @Test
    public void test3() {
        double d1 = 0.000000045;
        System.out.println(d1); // 4.5E-8
        BigDecimal bd1 = new BigDecimal(d1).setScale(9, RoundingMode.HALF_UP);
        System.out.println(bd1.toPlainString()); // 0.000000045
    }
}
