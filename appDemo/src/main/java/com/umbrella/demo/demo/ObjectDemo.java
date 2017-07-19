package com.umbrella.demo.demo;

import org.junit.Test;

/**
 * Created by xudazhou on 2017/7/10.
 */
public class ObjectDemo {

    @Test
    public void test1() {
        Object obj1 = new String("a");
        System.out.println(obj1); // a
        System.out.println(obj1.getClass()); // class java.lang.String
    }
}
