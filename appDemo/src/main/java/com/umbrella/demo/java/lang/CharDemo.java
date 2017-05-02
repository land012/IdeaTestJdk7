package com.umbrella.demo.java.lang;

import org.junit.Test;

/**
 * Created by xudazhou on 2017/3/26.
 */
public class CharDemo {

    @Test
    public void test1() {
        char c1 = '中';
        System.out.println(c1); // 中
        Character ct1 = new Character(c1);
        System.out.println(ct1.toString().getBytes().length); // 3
    }
}
