package com.umbrella.java.lang;

import org.junit.Test;

/**
 * Created by 大洲 on 14-12-18.
 */
public class IntegerTest {
    @Test
    public void test1() {
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        System.out.println(i1==i2); // false
        System.out.println(i1.equals(i2)); // true
    }
}
