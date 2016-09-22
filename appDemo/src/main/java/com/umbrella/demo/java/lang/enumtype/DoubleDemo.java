package com.umbrella.demo.java.lang.enumtype;

import org.junit.Test;

/**
 * Created by xudazhou on 2015/8/31.
 */
public class DoubleDemo {
    @Test
    public void test1() {
        double d1 = 3d; // 双精度浮点数表示法

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
}
