package com.umbrella.queue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2017/8/4.
 */
public class LinkedBlockingQueueDemo {

    private static Logger log = LoggerFactory.getLogger(LinkedBlockingQueueDemo.class);

    /**
     * put("c") 时会阻塞
     */
    @Test
    public void test1() {
        try {
            BlockingQueue<String> q1 = new LinkedBlockingQueue<>(2);

            new Thread(new MyThread(q1)).start();

            q1.put("a");
            q1.put("b");
            q1.put("c");
        } catch (InterruptedException e) {
            log.info("", e);
        }


        try {
            TimeUnit.MINUTES.sleep(5);
        } catch (InterruptedException e) {
            log.info("", e);
        }
    }

    class MyThread implements Runnable {

        private BlockingQueue<String> bq1;

        public MyThread(BlockingQueue<String> bq1) {
            this.bq1 = bq1;
        }

        @Override
        public void run() {
            while (true) {
                log.info("queue size is {}", bq1.size());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    log.info("", e);
                }
            }
        }
    }
}
