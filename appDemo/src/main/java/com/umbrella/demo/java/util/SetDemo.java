package com.umbrella.demo.java.util;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 大洲 on 15-7-5.
 */
public class SetDemo {
    @Test
    public void test1() {
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(1);
        System.out.println(set1.size()); // 2
        System.out.println(set1); // [1, 2]
    }
}
