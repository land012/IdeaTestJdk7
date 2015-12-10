package com.umbrella.demo.java.math;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by xudazhou on 2015/7/27.
 */
public class BigDecimalDemo {
    @Test
    public void test1() {
        BigDecimal bd1 = new BigDecimal(-1);
        System.out.println(bd1);
        System.out.println(bd1.abs());
    }

    @Test
    public void test2() {
        BigDecimal b1 = new BigDecimal(0);
        BigDecimal b2 = new BigDecimal(0.000000);
        BigDecimal b3 = new BigDecimal("0.000000");

        System.out.println(b1); // 0
        System.out.println(b2); // 0
        System.out.println(b3); // 0.000000

        System.out.println(b1.equals(b2)); // true
        System.out.println(BigDecimal.ZERO.equals(b1)); // true
        System.out.println(BigDecimal.ZERO.equals(b2)); // true
        System.out.println(b1.equals(b3)); // false
        System.out.println(BigDecimal.ZERO.equals(b3)); // false
    }
}
