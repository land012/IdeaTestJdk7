package com.umbrella.grammar.thread;

import org.apache.log4j.Logger;

/**
 * Created by 大洲 on 15-6-4.
 * 主线程被 子线程 阻塞，但三个线程都在并发执行
 * 这样，主线程被等待所有线程执行完成后继续执行，起到了 CoutDownLatch 的作用
 */
public class JoinDemo {

    private static final Logger log = Logger.getLogger(JoinDemo.class);

    public static void main(String[] args) {
        log.info("I am main begin");

        try {
            Thread t1 = new Thread(new Task(1));
            Thread t2 = new Thread(new Task(2));
            Thread t3 = new Thread(new Task(3));
            t1.start();
            t2.start();
            t3.start();
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("I am main end");
    }

    static class Task implements Runnable {

        private long id;

        Task(long id) {
            this.id = id;
        }

        @Override
        public void run() {
            log.info(String.format("I am task %d begin", id));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(String.format("I am task %d end", id));
        }
    }
}
