package com.umbrella.demo.java.demo;

import org.junit.Test;

/**
 * Created by xudazhou on 2015/8/25.
 */
public class ClasspathDemo {
    /**
     * 获取 classpath
     */
    @Test
    public void test1() {
        // /D:/_idea/TestApp/appDemo/target/classes/
        System.out.println(ClassLoader.getSystemResource("").getPath());
        // /D:/_idea/TestApp/appDemo/target/classes/
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        // /D:/_idea/TestApp/appDemo/target/classes/com/umbrella/demo/java/demo/
        System.out.println(this.getClass().getResource("").getPath());
        // /D:/_idea/TestApp/appDemo/target/classes/
        System.out.println(this.getClass().getResource("/").getPath());
    }
}
