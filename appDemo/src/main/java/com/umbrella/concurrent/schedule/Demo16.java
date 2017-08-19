package com.umbrella.concurrent.schedule;

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
 2015-05-29 12:18:41,704 INFO [pool-1-thread-1] Demo15$Task.run(34) | I am task 1 begin
 2015-05-29 12:18:47,705 INFO [pool-1-thread-1] Demo15$Task.run(44) | I am task 1 end
 2015-05-29 12:18:47,711 INFO [pool-1-thread-1] Demo15$Task.run(34) | I am task 2 begin
 2015-05-29 12:18:53,711 INFO [pool-1-thread-1] Demo15$Task.run(44) | I am task 2 end
 2015-05-29 12:18:53,711 INFO [pool-1-thread-1] Demo15$Task.run(34) | I am task 3 begin
 2015-05-29 12:18:59,711 INFO [pool-1-thread-1] Demo15$Task.run(44) | I am task 3 end
 2015-05-29 12:18:59,711 INFO [pool-1-thread-1] Demo15$Task.run(34) | I am task 4 begin
 2015-05-29 12:19:02,711 INFO [pool-1-thread-1] Demo15$Task.run(44) | I am task 4 end
 2015-05-29 12:19:02,711 INFO [pool-1-thread-1] Demo15$Task.run(34) | I am task 5 begin
 2015-05-29 12:19:05,711 INFO [pool-1-thread-1] Demo15$Task.run(44) | I am task 5 end
 2015-05-29 12:19:06,702 INFO [pool-1-thread-1] Demo15$Task.run(34) | I am task 6 begin
 2015-05-29 12:19:09,703 INFO [pool-1-thread-1] Demo15$Task.run(44) | I am task 6 end
 2015-05-29 12:19:11,703 INFO [pool-1-thread-1] Demo15$Task.run(34) | I am task 7 begin
 2015-05-29 12:19:14,703 INFO [pool-1-thread-1] Demo15$Task.run(44) | I am task 7 end
 2015-05-29 12:19:16,702 INFO [pool-1-thread-1] Demo15$Task.run(34) | I am task 8 begin
 */
public class Demo16 {

    private static final Logger log = Logger.getLogger(Demo16.class);

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
                if(i>=1 && i<=3) {
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
