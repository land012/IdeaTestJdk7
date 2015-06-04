package com.umbrella.demo.concurrent.schedule;

import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 大洲 on 15-5-29.
 * scheduleAtFixedRate
 * 任务执行时长大于固定频率
 * initialDelay=0
 * 第一次会立即执行
 * 由于任务执行时长超过了固定频率，下一次会立即执行，以此类推（但不会并发执行，也就是说，不会上一次没执行完，下一次就开始执行）
 * 也就是说，实际执行频率已经变成了任务执行时长
 */
public class Demo13 {

    private static final Logger log = Logger.getLogger(Demo13.class);

    public static void main(String[] args) {
        log.info("I ma main begin");
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(new Task(), 0, 5, TimeUnit.SECONDS);
        log.info("I ma main end");
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            log.info("I am task begin");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("I am task end");
        }
    }
}
