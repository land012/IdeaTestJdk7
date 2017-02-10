package com.umbrella.demo.java.lang;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xudazhou on 2017/2/10.
 */
public class AtomicIntegerDemo {

    /**
     * 当达到最大值时，再+1
     */
    @Test
    public void test1() {
        AtomicInteger i1 = new AtomicInteger(2147483646);
        System.out.println(i1.getAndIncrement()); // 2147483646
        System.out.println(i1.getAndIncrement()); // 2147483647
        System.out.println(i1.getAndIncrement()); // -2147483648
        System.out.println(i1.getAndIncrement()); // -2147483647
    }

    /**
     * 当达到最大值时，再+1
     */
    @Test
    public void test2() {
        AtomicInteger i1 = new AtomicInteger(-2);
        System.out.println(i1.getAndIncrement()); // -2
        System.out.println(i1.getAndIncrement()); // -1
        System.out.println(i1.getAndIncrement()); // 0
        System.out.println(i1.getAndIncrement()); // 1
    }
}
