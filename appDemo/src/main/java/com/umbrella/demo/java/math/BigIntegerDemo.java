package com.umbrella.demo.java.math;

import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by xudazhou on 2017/1/12.
 */
public class BigIntegerDemo {

    /**
     * Long.MAX_VALUE 922 3372 0368 5477 5807
     */
    @Test
    public void test1() {
        BigInteger i1 = new BigInteger("9223372036854775808");
        System.out.println(i1);
    }

    /**
     * 数据 10689576657988959934 已经超过了 long 的最大值
     * 转成 string
     */
    @Test
    public void test2() {
        BigInteger bi1 = new BigInteger("10689576657988959934");
        System.out.println(bi1); // 10689576657988959934
        long l1 = bi1.longValue();
        System.out.println(l1); // -7757167415720591682
        BigInteger bi2 = BigInteger.valueOf(l1);
        System.out.println(bi2); // -7757167415720591682

        // 方式1
        BigInteger bi3 = BigInteger.valueOf(l1 & 0x7FFFFFFFFFFFFFFFL);
        System.out.println(bi3); // 1466204621134184126
        BigInteger bi4 = bi3.setBit(63);
        System.out.println(bi4); // 10689576657988959934

        // 方式2
        BigInteger bi5 = new BigInteger("1");
        bi5 = bi5.shiftLeft(64);
        bi5 = bi5.add(BigInteger.valueOf(l1));
        System.out.println(bi5.toString()); // 10689576657988959934
    }

    /**
     * setBit 将某位指定为 1，
     * 索引从 0 开始
     */
    @Test
    public void test3() {
        BigInteger bi1 = new BigInteger("0");
        BigInteger bi2 = bi1.setBit(0);
        BigInteger bi3 = bi1.setBit(1);
        System.out.println(bi2); // 1
        System.out.println(bi3); // 2

        BigInteger bi21 = new BigInteger("5");
        BigInteger bi22 = bi21.setBit(1);
        BigInteger bi23 = bi21.setBit(2);
        System.out.println(bi22); // 7
        System.out.println(bi23); // 5
    }

}
