package com.umbrella.demo.concurrent.schedule;

import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 大洲 on 15-5-29.
 * scheduleAtFixedRate
 * 任务执行时长大时大于固定频率，有时小于固定频率
 * 如果下次执行时，已经过了原来的频率，则会立即执行
 * 否则在约定的时间点执行
 *
 2015-05-29 11:37:25,201 INFO [pool-1-thread-1] Demo14$Task.run(35) | I am task 1 begin
 2015-05-29 11:37:28,201 INFO [pool-1-thread-1] Demo14$Task.run(45) | I am task 1 end
 2015-05-29 11:37:30,200 INFO [pool-1-thread-1] Demo14$Task.run(35) | I am task 2 begin
 2015-05-29 11:37:36,200 INFO [pool-1-thread-1] Demo14$Task.run(45) | I am task 2 end
 2015-05-29 11:37:36,200 INFO [pool-1-thread-1] Demo14$Task.run(35) | I am task 3 begin
 2015-05-29 11:37:39,201 INFO [pool-1-thread-1] Demo14$Task.run(45) | I am task 3 end
 2015-05-29 11:37:40,199 INFO [pool-1-thread-1] Demo14$Task.run(35) | I am task 4 begin
 2015-05-29 11:37:46,200 INFO [pool-1-thread-1] Demo14$Task.run(45) | I am task 4 end
 2015-05-29 11:37:46,200 INFO [pool-1-thread-1] Demo14$Task.run(35) | I am task 5 begin
 2015-05-29 11:37:49,200 INFO [pool-1-thread-1] Demo14$Task.run(45) | I am task 5 end
 2015-05-29 11:37:50,199 INFO [pool-1-thread-1] Demo14$Task.run(35) | I am task 6 begin
 */
public class Demo14 {

    private static final Logger log = Logger.getLogger(Demo14.class);

    public static void main(String[] args) {
        log.info("I ma main begin");
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(new Task(), 0, 5, TimeUnit.SECONDS);
        log.info("I ma main end");
    }

    static class Task implements Runnable {

        private long i = 1;

        @Override
        public void run() {
            log.info("I am task " + i + " begin");
            try {
                if(i%2==0) {
                    Thread.sleep(6000);
                } else {
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("I am task " + i + " end");
            i++;
        }
    }
}
