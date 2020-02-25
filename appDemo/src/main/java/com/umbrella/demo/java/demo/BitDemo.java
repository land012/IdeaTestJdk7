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

    @Test
    public void test3() {
        System.out.println(Integer.numberOfLeadingZeros(3)); // 30 # 00000000,00000000,00000000,00000011
        System.out.println(Integer.numberOfLeadingZeros(128)); // 24 # 3个字节
    }
}
