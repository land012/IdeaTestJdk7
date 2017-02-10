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
}
