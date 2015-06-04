package com.umbrella.demo.concurrent.executors;

import org.apache.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 大洲 on 15-5-28.
 * 多级线程
 * 当Task2没有执行完时，Task1不会结束，所以 main 不会结束
 */
public class Demo6 {

    private static final Logger log = Logger.getLogger(Demo6.class);

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.submit(new Task1(latch));
        exec.shutdown();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("I am main end");
    }

    static class Task1 implements Runnable {

        private CountDownLatch latch;

        Task1(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            ExecutorService exec = Executors.newFixedThreadPool(2);
            exec.submit(new Task2());
            exec.shutdown();
            latch.countDown();
            log.info("I am Task1 end");
        }
    }

    static class Task2 implements Runnable {
        @Override
        public void run() {
            log.info("I am Task2 begin");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("I am Task2 end");
        }
    }
}
