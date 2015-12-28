package com.umbrella.demo.akka.concur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xudazhou on 2015/12/26.
 * 普通线程 没有防止并发
 */
public class MyThread implements Callable<String> {

    private static final Logger log = LoggerFactory.getLogger(MyThread.class);

    private Object obj;
    private CountDownLatch countDownLatch;

    public MyThread(Object obj, CountDownLatch countDownLatch) {
        this.obj = obj;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public String call() throws Exception {
        try {
            if(obj instanceof MyCounter) {
                MyCounter myCounter = (MyCounter)obj;
                countDownLatch.await();
                myCounter.incr();
//                log.info("myCounter is " + myCounter);
            }
        } catch (InterruptedException e) {
            log.error("", e);
        }
        return null;
    }
}
