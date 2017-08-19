package com.umbrella.concurrent.schedule;

import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 大洲 on 15-5-29.
 * scheduleWithFixedDelay
 * 任务执行时长大于固定频率
 * 下一次执行开始距上一次结束的时间间隔是5秒
 2015-05-29 12:24:46,482 INFO [pool-1-thread-1] Demo23$Task.run(30) | I am task begin
 2015-05-29 12:24:52,483 INFO [pool-1-thread-1] Demo23$Task.run(36) | I am task end
 2015-05-29 12:24:57,490 INFO [pool-1-thread-1] Demo23$Task.run(30) | I am task begin
 2015-05-29 12:25:03,491 INFO [pool-1-thread-1] Demo23$Task.run(36) | I am task end
 2015-05-29 12:25:08,492 INFO [pool-1-thread-1] Demo23$Task.run(30) | I am task begin
 2015-05-29 12:25:14,492 INFO [pool-1-thread-1] Demo23$Task.run(36) | I am task end
 2015-05-29 12:25:19,493 INFO [pool-1-thread-1] Demo23$Task.run(30) | I am task begin
 2015-05-29 12:25:25,494 INFO [pool-1-thread-1] Demo23$Task.run(36) | I am task end
 2015-05-29 12:25:30,495 INFO [pool-1-thread-1] Demo23$Task.run(30) | I am task begin
 */
public class Demo23 {

    private static final Logger log = Logger.getLogger(Demo23.class);

    public static void main(String[] args) {
        log.info("I ma main begin");
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleWithFixedDelay(new Task(), 0, 5, TimeUnit.SECONDS);
        log.info("I ma main end");
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            log.info("I am task begin");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("I am task end");
        }
    }
}
