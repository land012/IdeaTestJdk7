package com.umbrella.demo.akka.concur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xudazhou on 2015/12/26.
 * 演示并发出错的情况
 */
public class DemoCommonConcur {

    private static final Logger log = LoggerFactory.getLogger(DemoCommonConcur.class);

    public static void main(String[] args) throws Exception {
        MyCounter myCounter = new MyCounter(0);
        CountDownLatch countDownLatch = new CountDownLatch(1);

        ExecutorService exec = Executors.newFixedThreadPool(100);

        List<Future<String>> futureList = new ArrayList<>();

        for(int i=0; i<100000; i++) {
            futureList.add(exec.submit(new MyThread(myCounter, countDownLatch)));
        }

        countDownLatch.countDown();

        exec.shutdown();

        for(Future<String> f : futureList) {
            String s = f.get();
        }

        log.info("myCounter is " + myCounter.get()); // myCounter is 99991

    }
}
