package com.umbrella.demo;

import org.junit.Test;

/**
 * create by xudazhou 2018/3/26
 */
public class ThreadLocalDemo {

    private ThreadLocal<String> tl1 = new ThreadLocal<>();

    @Test
    public void test1() {
        tl1.set("aa");
        
        System.out.println(tl1.get());
        tl1.remove();
    }
}
