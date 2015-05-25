package com.umbrella.demo.java.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 大洲 on 15-1-23.
 */
public class ListDemo {
    /**
     * 验证 contains()方法
     */
    @Test
    public void test1() {
        List<String> list1 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        System.out.println(list1.contains("a")); // true
    }

    /**
     * 拿到的下标超界时，会抛异常
     * java.lang.IndexOutOfBoundsException
     */
    @Test
    public void test2() {
        List<String> list1 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        System.out.println(list1.get(2));
    }

    @Test
    public void test3() {
        List<String> list1 = new ArrayList<String>();
        list1.addAll(null); // java.lang.NullPointerException
    }
}
