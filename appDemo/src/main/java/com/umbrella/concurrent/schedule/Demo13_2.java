package com.umbrella.concurrent.schedule;

import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 大洲 on 15-5-29.
 * scheduleAtFixedRate
 * 线程池线程数为 2，任务执行时长大于固定频率
 * 即使线程池中有多个线程，任务也不会并发执行
 2015-05-29 12:51:43,732 INFO [pool-1-thread-1] Demo13_2$Task.run(33) | I am task 1 begin
 2015-05-29 12:51:53,732 INFO [pool-1-thread-1] Demo13_2$Task.run(39) | I am task 1 end
 2015-05-29 12:51:53,740 INFO [pool-1-thread-1] Demo13_2$Task.run(33) | I am task 2 begin
 2015-05-29 12:52:03,741 INFO [pool-1-thread-1] Demo13_2$Task.run(39) | I am task 2 end
 2015-05-29 12:52:03,741 INFO [pool-1-thread-1] Demo13_2$Task.run(33) | I am task 3 begin
 2015-05-29 12:52:13,742 INFO [pool-1-thread-1] Demo13_2$Task.run(39) | I am task 3 end
 2015-05-29 12:52:13,742 INFO [pool-1-thread-2] Demo13_2$Task.run(33) | I am task 4 begin
 */
public class Demo13_2 {

    private static final Logger log = Logger.getLogger(Demo13_2.class);

    public static void main(String[] args) {
        log.info("I ma main begin");
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(2);
        exec.scheduleAtFixedRate(new Task(), 0, 5, TimeUnit.SECONDS);
        log.info("I ma main end");
    }

    static class Task implements Runnable {

        private long i=1;

        @Override
        public void run() {
            log.info(String.format("I am task %s begin", i));
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(String.format("I am task %s end", i));
            i++;
        }
    }
}
