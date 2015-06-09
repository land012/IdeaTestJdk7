package com.umbrella.grammar.demo;

import org.junit.Test;

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
}
