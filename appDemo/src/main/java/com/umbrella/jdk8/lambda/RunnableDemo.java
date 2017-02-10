package com.umbrella.jdk8.lambda;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2016/12/23.
 */
public class RunnableDemo {
    @Test
    public void test1() throws Exception {
        new Thread(() -> {
            System.out.println("I am lambda runnable");
            System.out.println("haha");
        }).start();

        TimeUnit.SECONDS.sleep(2);
    }
}
