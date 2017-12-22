package com.umbrella.concurrent.submit;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xudazhou on 2016/2/24.
 * 验证能否在 子线程里打断点
 * 可以
 */
public class ThreadPoolDemo {
    @Test
    public void test1() {
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.submit(new Task());
        exec.shutdown();

        try {
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(1);
            System.out.println(2);
            System.out.println(3);
        }
    }
}
