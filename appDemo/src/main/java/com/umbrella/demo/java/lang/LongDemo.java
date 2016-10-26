package com.umbrella.demo.java.lang;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Created by 大洲 on 14-11-10.
 */
public class LongDemo {
    public static void main(String[] args) {
//        DecimalFormat df1 = new DecimalFormat("0.0");
//        System.out.println(df1.format(Math.pow(2, 32))); // 4294967296.0
//        System.out.println(Integer.MAX_VALUE);         // 2147483647
//        System.out.println(Long.MAX_VALUE);            // 922 3372 0368 5477 5807

        /**
         * long 转 int
         */
        long l1 = 2147483647L;
        long l2 = 2147483648L;
        long l3 = 2147483649L;
        long l4 = 2147483650L;
        System.out.println((int)l1); // 2147483647
        System.out.println((int)l2); // -2147483648
        System.out.println((int)l3); // -2147483647
        System.out.println((int)l4); // -2147483646

        System.out.println("Integer.MAX_VALUE=" + Integer.MAX_VALUE); // 2147483647
        System.out.println(new DecimalFormat("0").format(Math.pow(2, 31)-1)); // 2147483647
        System.out.println(Math.pow(2, 17)-1); // 131071.0
        System.out.println(new DecimalFormat("0").format(Math.pow(2, 16))); // 65536

    }

    /**
     *
     */
    @Test
    public void test2() {
        long l1 = 127; // 0111 1111
        System.out.println(Long.valueOf(l1).byteValue()); // 127
        long l2 = 128; // 1000 0000
        System.out.println(Long.valueOf(l2).byteValue()); // -128
    }

    /**
     * reverse 方法
     */
    @Test
    public void test3() {
        long l1 = 127; // 0111 1111
        System.out.println(Long.reverse(l1));
    }

    /**
     * java.lang.NumberFormatException: null
     */
    @Test
    public void test4() {
        long l1 = 0;
//        Long.valueOf(null);
//        l1 = Long.valueOf(null);
        System.out.println("哈哈");
    }

    /**
     * 整数除法 /，默认保留整数部分
     */
    @Test
    public void test5() {
        long l1 = 10/3;
        System.out.println(l1);
        System.out.println(10 * 1.0/3);
    }

    /**
     * java.lang.NullPointerException
     */
    @Test
    public void test6() {
        Long l1 = null;
        System.out.println(l1-1);
    }

    @Test
    public void test7() {
        Long l1 = new Long(5);
        Long l2 = new Long(2);
        System.out.println(l1-1); // 4
        System.out.println(l1%l2); // 1
    }

    @Test
    public void test9() {
        System.out.println(Long.MAX_VALUE); // 922 3372 0368 5477 5807
        System.out.println(1);
        System.out.println(2.1);
        System.out.println(2.2);
        System.out.println(3);
    }

}
