package com.umbrella.demo.java.lang;

import org.junit.Test;

/**
 * Created by xudazhou on 2016/4/22.
 */
public class StringBuilderDemo {

    /**
     * append(null) 时不会抛异常
     */
    @Test
    public void test1() {
        StringBuilder sb = new StringBuilder();
        String str1 = null;
        sb.append("k1=")
                .append(str1);
        System.out.println(sb);
    }

    @Test
    public void test2() {
        StringBuilder sb1 = new StringBuilder("abcde");
        System.out.println(sb1.substring(0, sb1.length()-1)); // abcd
    }

    @Test
    public void test3() {
        StringBuilder sb1 = new StringBuilder("abcdefg哈哈");
        System.out.println(sb1.length());
    }
}
