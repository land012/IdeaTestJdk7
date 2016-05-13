package com.umbrella.demo.concurrent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by xudazhou on 2016/5/12.
 */
public class FutureTaskDemo {

    private static final Logger log = LoggerFactory.getLogger(FutureTaskDemo.class);

    /**
     * FutureTask
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("I am FutureTask begin");
                Thread.sleep(3000);
                log.info("I am FutureTask end");
                return "f1";
            }
        });
        new Thread(futureTask).start();
        log.info("I am main");
        log.info(futureTask.get());
    }
}
