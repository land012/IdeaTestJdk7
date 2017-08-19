package com.umbrella.concurrent.schedule;

import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 大洲 on 15-5-29.
 * scheduleAtFixedRate
 * 任务执行时长小于约定间隔
 2015-05-29 12:47:29,761 INFO [pool-1-thread-1] Demo12$Task.run(31) | I am task 1 begin
 2015-05-29 12:47:32,762 INFO [pool-1-thread-1] Demo12$Task.run(37) | I am task 1 end
 2015-05-29 12:47:34,752 INFO [pool-1-thread-1] Demo12$Task.run(31) | I am task 2 begin
 2015-05-29 12:47:37,752 INFO [pool-1-thread-1] Demo12$Task.run(37) | I am task 2 end
 2015-05-29 12:47:39,751 INFO [pool-1-thread-1] Demo12$Task.run(31) | I am task 3 begin
 2015-05-29 12:47:42,751 INFO [pool-1-thread-1] Demo12$Task.run(37) | I am task 3 end
 2015-05-29 12:47:44,750 INFO [pool-1-thread-1] Demo12$Task.run(31) | I am task 4 begin
 2015-05-29 12:47:47,750 INFO [pool-1-thread-1] Demo12$Task.run(37) | I am task 4 end
 2015-05-29 12:47:49,750 INFO [pool-1-thread-1] Demo12$Task.run(31) | I am task 5 begin
 */
public class Demo12 {

    private static final Logger log = Logger.getLogger(Demo12.class);

    public static void main(String[] args) {
        log.info("I ma main begin");
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(new Task(), 0, 5, TimeUnit.SECONDS);
        log.info("I ma main end");
    }

    static class Task implements Runnable {

        private long i=1;

        @Override
        public void run() {
            log.info(String.format("I am task %s begin", i));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(String.format("I am task %s end", i));
            i++;
        }
    }
}
