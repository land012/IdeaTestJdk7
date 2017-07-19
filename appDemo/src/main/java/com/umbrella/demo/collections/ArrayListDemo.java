package com.umbrella.demo.collections;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by xudazhou on 2017/6/28.
 */
public class ArrayListDemo {

    /**
     * 异常
     * java.lang.IndexOutOfBoundsException: Index: 5, Size: 0
     */
    @Test
    public void test1() {
        ArrayList<String> list1 = new ArrayList<>(16);
        list1.add(5, "e");
        System.out.println(list1);
    }

    @Test
    public void test2() {
        ArrayList<String> list1 = new ArrayList<>(16);
        list1.add(0, "a");
        list1.add(0, "b");
        System.out.println(list1); // [b, a]
    }
}
