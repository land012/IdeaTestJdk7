package com.umbrella.math;

import org.junit.Test;

/**
 * Created by 大洲 on 15-1-22.
 */
public class OddEvenDemo {
    /**
     * 偶数*2 + 1
     * 奇数*2 + 1
     * 如果两个数差1，那么乘以2后，两个数的差为2
     */
    @Test
    public void test1() {
        for(int i=0; i<10; i++) {
            System.out.println(i*2+1);
        }
    }

    /**
     * 得到的数必然是 0 或 2
     */
    @Test
    public void test2() {
        for(int i=0; i<10; i++) {
            String format = "%s ";
            if(i==9) format = "%s \n";
            System.out.format(format, i%2*2);
        }
    }

    /**
     * 得到的数必然是 1 或 -1
     */
    @Test
    public void test3() {
        for(int i=0; i<10; i++) {
            System.out.println(1-i%2*2);
        }
    }
}
