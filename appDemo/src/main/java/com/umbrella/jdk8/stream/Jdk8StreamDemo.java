package com.umbrella.jdk8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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

    /**
     * 保留了 filter 到的元素
     */
    @Test
    public void testFilter() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        List<String> list2 = new ArrayList<>();
        list2.add("c");
        list2.add("d");

        // [c]
        System.out.println(list1.stream().filter(list2::contains).collect(Collectors.toList()));
    }

    /**
     * java.lang.NullPointerException
     */
    @Test
    public void testFilter2() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        List<String> list2 = null;

        // [c]
        System.out.println(list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toList()));
    }

    /**
     * 没有交集的情况
     */
    @Test
    public void testFilter2_2() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        List<String> list2 = new ArrayList<>();
        list2.add("d");
        list2.add("e");

        // []
        System.out.println(list1.stream()
                .filter(e -> list2.contains(e))
                .collect(Collectors.toList()));
    }

    /**
     * 过滤掉 filter 的元素
     */
    @Test
    public void testFilter3() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        List<String> list2 = new ArrayList<>();
        list2.add("c");
        list2.add("d");

        // [a, b]
        System.out.println(list1.stream()
                .filter(e -> !list2.contains(e))
                .collect(Collectors.toList()));
    }
}
