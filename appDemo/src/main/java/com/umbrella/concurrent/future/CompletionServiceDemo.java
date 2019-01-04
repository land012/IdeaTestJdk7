package com.umbrella.concurrent.future;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * Created by xudazhou on 2017/8/20.
 */
public class CompletionServiceDemo {

    private static Logger log = LoggerFactory.getLogger(CompletionServiceDemo.class);

    @Test
    public void test1() throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        CompletionService<String> cs = new ExecutorCompletionService<>(exec);

        cs.submit(() -> {
            log.info("i am {} begin", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(6);
            log.info("i am {} end", Thread.currentThread().getName());
            return "ok1";
        });
        cs.submit(() -> {
            log.info("i am {} begin", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            log.info("i am {} end", Thread.currentThread().getName());
            return "ok2";
        });
        cs.submit(() -> {
            log.info("i am {} begin", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
            log.info("i am {} end", Thread.currentThread().getName());
            return "ok3";
        });

        // 实际业务中，不用能 while true，当所有任务都返回结果时，take() 方法会阻塞
        // 可以记录 submit 的次数
        while (true) {
            log.info(cs.take().get());
        }
    }
}
