package com.umbrella.demo.java.math;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by xudazhou on 2015/7/27.
 */
public class BigDecimalDemo {
    /**
     * 绝对值
     */
    @Test
    public void test1() {
        BigDecimal bd1 = new BigDecimal(-1);
        System.out.println(bd1);
        System.out.println(bd1.abs());
    }

    /**
     * BigDecimal 0
     */
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
        System.out.println(BigDecimal.ZERO.compareTo(b3)); // 0
    }

    /**
     *  valueOf 方法的 scale
     */
    @Test
    public void test3() {
        System.out.println("======================= begin ============================");
        System.out.println(new BigDecimal(200.234).setScale(2, RoundingMode.HALF_UP)); // 200.23
        System.out.println(BigDecimal.valueOf(200, 2)); // 2
        System.out.println("======================= end ============================");
    }

    /**
     * BigDecimal 可以保存超过 long 的值
     * 超过 long 的最大值 9223372036854775807
     */
    @Test
    public void test4() {
        BigDecimal b1 = new BigDecimal("19223372036854775807");
        System.out.println(b1);
        System.out.println(b1.longValue());
    }

    @Test
    public void test5() {
        double d1 = 1.23549345d;
        BigDecimal bd1 = new BigDecimal(d1);
        BigDecimal bd2 = bd1.setScale(4, RoundingMode.HALF_UP);
        System.out.println(bd1.doubleValue()); // 1.23549345
        System.out.println(bd2.doubleValue()); // 1.2355

        double d2 = 0.1230001;
        BigDecimal bd2_1 = new BigDecimal(d2);
        System.out.println(bd2_1.setScale(4, RoundingMode.HALF_UP)); // 0.1230
    }
}
