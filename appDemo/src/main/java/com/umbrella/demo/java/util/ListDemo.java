package com.umbrella.demo.java.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

    /**
     * 遍历时修改 抛出异常
     * java.util.ConcurrentModificationException
     */
    @Test
    public void test4() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        Iterator<String> it1 = list1.iterator();
        while (it1.hasNext()) {
            String str1 = it1.next();
            if("b".equals(str1)) {
//                list1.remove(str1);
                list1.add("bb");
            }
        }
    }

    /**
     * 不会抛异常
     */
    @Test
    public void test5() {
        List<String> list1 = new CopyOnWriteArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        // 只打印4个元素
//        Iterator<String> it1 = list1.iterator();
//        while (it1.hasNext()) {
//            String str1 = it1.next();
//            if("b".equals(str1)) {
//                list1.add("bb");
//            }
//            System.out.println(String.format("%s-%s-%s", list1.hashCode(), list1.size(), str1));
//        }

        // 会打印5个元素
        for(int i=0; i<list1.size(); i++) {
            String str1 = list1.get(i);
            if("b".equals(str1)) {
                list1.add("bb");
            }
            System.out.println(String.format("%s-%s-%s", list1.hashCode(), list1.size(), str1));
        }
    }

    /**
     * subList
     */
    @Test
    public void test6() {
        List<String> list1 = new CopyOnWriteArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        List<String> list2 = list1.subList(1, 2);
        System.out.println(list1); // [a, b, c, d]
        System.out.println(list2); // [b]
    }
}
