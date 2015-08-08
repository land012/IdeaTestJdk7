package com.umbrella.demo.java.lang;

import org.junit.Test;

/**
 * Created by xudazhou on 2015/8/5.
 */
public class RuntimeDemo {
    @Test
    public void test1() {
        System.out.println("JVM配置的最大内存：" + Runtime.getRuntime().maxMemory()/(1024.0*1024) + "M");
        System.out.println("当前分配的总内存：" + Runtime.getRuntime().totalMemory()/(1024.0*1024) + "M");
        System.out.println("当前分配的总内存的可用内存：" + Runtime.getRuntime().freeMemory()/(1024.0*1024) + "M");
    }
}
