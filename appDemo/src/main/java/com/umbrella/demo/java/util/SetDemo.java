package com.umbrella.demo.java.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 大洲 on 15-7-5.
 */
public class SetDemo {
    /**
     * 重复添加不会报错
     */
    @Test
    public void test1() {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(1);
        System.out.println(set1.size()); // 2
        System.out.println(set1); // [1, 2]
    }

    @Test
    public void test2() {
        Set<String> set1 = new HashSet<>();
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("a");
        set1.addAll(list1);
        System.out.println(list1); // [a, b, a]
        System.out.println(set1); // [a, b]
    }
}
