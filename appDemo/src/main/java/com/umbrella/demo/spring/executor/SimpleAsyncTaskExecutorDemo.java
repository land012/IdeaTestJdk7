package com.umbrella.demo.spring.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2016/7/15.
 * 每次都会启动一个的线程执行任务
 */
public class SimpleAsyncTaskExecutorDemo {

    private static final Logger log = LoggerFactory.getLogger(SimpleAsyncTaskExecutorDemo.class);

    public static void main(String[] args) {
        SimpleAsyncTaskExecutor exec = new SimpleAsyncTaskExecutor();
//        exec.setDaemon(true); // 设置为true的话，子任务没执行完，主线程就结束了
        exec.setConcurrencyLimit(2);
        exec.setThreadNamePrefix("simple-");

        for(int i=0; i<5; i++) {
            exec.execute(new Task(i));
        }

    }

    static class Task implements Runnable {

        private int id;

        public Task(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            log.info("I am " + id + " begin");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                log.info("", e);
            }
            log.info("I am " + id + " end");
        }
    }

}
