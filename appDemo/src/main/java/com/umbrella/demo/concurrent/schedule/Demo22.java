package com.umbrella.demo.concurrent.schedule;

import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 大洲 on 15-5-29.
 * scheduleWithFixedDelay
 * 任务执行时长小于延迟（执行间隔与任务时长无关）
 * 下一次执行开始距上一次结束的时间间隔是5秒
 2015-05-29 12:42:23,454 INFO [pool-1-thread-1] Demo22$Task.run(30) | I am task 1 begin
 2015-05-29 12:42:26,455 INFO [pool-1-thread-1] Demo22$Task.run(36) | I am task 1 end
 2015-05-29 12:42:31,465 INFO [pool-1-thread-1] Demo22$Task.run(30) | I am task 2 begin
 2015-05-29 12:42:34,465 INFO [pool-1-thread-1] Demo22$Task.run(36) | I am task 2 end
 2015-05-29 12:42:39,466 INFO [pool-1-thread-1] Demo22$Task.run(30) | I am task 3 begin
 2015-05-29 12:42:42,466 INFO [pool-1-thread-1] Demo22$Task.run(36) | I am task 3 end
 2015-05-29 12:42:47,467 INFO [pool-1-thread-1] Demo22$Task.run(30) | I am task 4 begin
 2015-05-29 12:42:50,467 INFO [pool-1-thread-1] Demo22$Task.run(36) | I am task 4 end
 2015-05-29 12:42:55,468 INFO [pool-1-thread-1] Demo22$Task.run(30) | I am task 5 begin
 */
public class Demo22 {

    private static final Logger log = Logger.getLogger(Demo22.class);

    public static void main(String[] args) {
        log.info("I ma main begin");
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleWithFixedDelay(new Task(), 0, 5, TimeUnit.SECONDS);
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
