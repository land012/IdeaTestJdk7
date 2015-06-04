package com.umbrella.demo.concurrent.schedule;

import org.apache.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 大洲 on 15-5-29.
 * scheduleAtFixedRate
 * initialDelay=3
 * 第一次会在3秒后执行
 */
public class Demo11 {

    private static final Logger log = Logger.getLogger(Demo11.class);

    public static void main(String[] args) {
        log.info("I ma main begin");
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(new Task(), 3, 5, TimeUnit.SECONDS);
        log.info("I ma main end");
    }

    static class Task implements Runnable {

        @Override
        public void run() {
            log.info("I am task begin");
            log.info("I am task end");
        }
    }
}
