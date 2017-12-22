package com.umbrella.demo.java.lang;

import org.junit.Test;

import java.util.Date;

/**
 * Created by 大洲 on 15-4-22.
 */
public class ClassDemo {
    @Test
    public void test1() {
        Date d1 = new Date();
        System.out.println(d1 instanceof Date); // true
        System.out.println(Date.class.isAssignableFrom(d1.getClass())); // true
    }

    @Test
    public void test2() {
        Date d1 = new Date();
        Date d2 = new Date();
        System.out.println(d1.getClass() == d2.getClass()); // true
    }
}
