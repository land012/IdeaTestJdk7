package com.umbrella.algorithm.roundrobin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xudazhou on 2016/10/25.
 * 轮询法
 * 无权重的 roundrobin
 */
public class SimpleRoundRobin<T> {

    private static final Logger log = LoggerFactory.getLogger(SimpleRoundRobin.class);

    private static final AtomicInteger next = new AtomicInteger(0);
    private T[] arr;

    public SimpleRoundRobin(T[] arr) {
        this.arr = arr;
    }

    public T select() throws Exception {
        if(arr == null || arr.length == 0) {
            throw new Exception("arr is empty");
        } else {
            return arr[next.getAndIncrement() % arr.length];
        }
    }

    public static void main(String[] args) throws Exception {
        String[] arr = { "a", "b", "c", "d", "e" };
        SimpleRoundRobin<String> srr = new SimpleRoundRobin<>(arr);
//        for(int i=0; i<10; i++) {
//            log.info(srr.select(arr));
//        }

        ExecutorService exec = Executors.newFixedThreadPool(3);
        for(int i=0; i<10; i++) {
            exec.execute(new MyRunnable(srr));
        }
        exec.shutdown();
    }

    static class MyRunnable implements Runnable {

        SimpleRoundRobin<String> srr;

        public MyRunnable(SimpleRoundRobin<String> srr) {
            this.srr = srr;
        }

        @Override
        public void run() {
            try {
                log.info(srr.select());
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }
}
