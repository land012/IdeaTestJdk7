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
}
