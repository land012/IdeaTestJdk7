package com.umbrella.demo.java.demo;

import org.junit.Test;

/**
 * 位操作
 * Created by xudazhou on 2016/4/26.
 */
public class BitDemo {
    /**
     * 左移
     */
    @Test
    public void test1() {
        System.out.println(1<<2); // 4
        System.out.println(3<<2); // 12
    }

    @Test
    public void test2() {
        byte b1 = 5;
        System.out.println(b1); // 5
        System.out.println(b1 & 255); // 5
    }

    /**
     * 前导 0 个数
     */
    @Test
    public void test3() {
        // 3 的二进制前面有 30个 0
        System.out.println(Integer.numberOfLeadingZeros(3)); // 30 # 00000000,00000000,00000000,00000011
        System.out.println(Integer.numberOfLeadingZeros(-3)); // 0 # 11111111,11111111,11111111,11111101
        System.out.println(Integer.toHexString(-3)); // ff,ff,ff,fd
        System.out.println(Integer.numberOfLeadingZeros(128)); // 24 # 3个字节
    }
}
