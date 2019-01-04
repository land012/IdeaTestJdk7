package com.umbrella.jdk8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by xudazhou on 2017/7/28.
 */
public class Jdk8StreamDemo {

    /**
     * findFirst
     * 只输出 a
     */
    @Test
    public void test1() {
        Stream.of("a", "b", "c")
                .findFirst()
                .map(s -> {
                    System.out.println(s);
                    return  s;
                });
    }

    /**
     * anyMatch
     * 所有都是 false，返回 false
     * 有一个是 true，返回 true
     */
    @Test
    public void testAnyMatch() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        System.out.println(list1.stream().anyMatch(e -> e == 5)); // false
        System.out.println(list1.stream().anyMatch(e -> e == 2)); // true
    }

    /**
     * noneMatch
     * 所有都是 false，返回 true
     * 有一个是 true，返回 false
     */
    @Test
    public void testNoneMatch() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        System.out.println(list1.stream().noneMatch(e -> e == 5)); // false
        System.out.println(list1.stream().noneMatch(e -> e == 2)); // true
    }
}
