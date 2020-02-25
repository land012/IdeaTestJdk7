package com.umbrella.grammar.demo;

import org.junit.Test;

import java.util.List;

/**
 * Created by 大洲 on 15-2-12.
 */
public class ForDemo {
    /**
     * 有两个条件
     */
    @Test
    public void test1() {
        int k = 7;
        for(int i=0; i<10 && i<k; i++) {
            System.out.println(i);
        }
    }

    /**
     * for 对 null 的处理
     */
    @Test
    public void test2() {
        List<String> list = null;
        // java.lang.NullPointerException
        for(String s : list) {
            System.out.println(s);
        }

    }

    @Test
    public void test3() {
        int i = 5;
        for (int j = 1; j < 3; --i) {
            j++;
        }
        System.out.println(i); // 3
    }
}
