package com.umbrella.concurrent.future;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by xudazhou on 2017/8/20.
 */
public class FutureDemo {

    private static Logger log = LoggerFactory.getLogger(FutureDemo.class);

    /**
     * 等待时长小于线程执行时长
     * get 方法会抛出 TimeoutException，但子线程并没有结束，继续get的话，仍然能返回正确的结果
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(1);

        Future<String> f = exec.submit(() -> {
            log.info("======================== begin");
            TimeUnit.SECONDS.sleep(5);
            log.info("======================== end");
            return "ok";
        });

        exec.shutdown();

        try {
            String res1 = f.get(3, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            log.error("" + e);
        }
        log.info("isDone={}", f.isDone()); // false
        String res = f.get();
        log.info(res); // ok
    }

    /**
     * get(timeout)
     * 指定的时间只是任务执行的时间，不会包括在队列里等待的时间包括第二任务不会超时
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(1);

        Future<String> f1 = exec.submit(() -> {
            log.info(Thread.currentThread().getName() + "======================== begin");
            TimeUnit.SECONDS.sleep(3);
            log.info(Thread.currentThread().getName() + "======================== end");
            return "ok1";
        });

        Future<String> f2 = exec.submit(() -> {
            log.info(Thread.currentThread().getName() + "======================== begin");
            TimeUnit.SECONDS.sleep(3);
            log.info(Thread.currentThread().getName() + "======================== end");
            return "ok2";
        });

        String res = f1.get(5, TimeUnit.SECONDS);
        log.info(res);
        res = f2.get(5, TimeUnit.SECONDS);
        log.info(res);
    }

    /**
     * 这种使用 List 获取子线程返回结果 Future 的处理方式，
     * 当前面有一个 Task 执行时间较长时，会影响后面子线程结果的获取
     * 但不会影响后面子线程(第三个子线程)的执行，因为当线程池中有子线程(第二个子线程)结束时，即便不从 Future 取出结果，也会从线程池退出，
     * 第三个Task 就可以执行了
     * 可以使用 CompletionService 解决获取结果顺序的问题
     */
    @Test
    public void test4() {
        ExecutorService exec = Executors.newFixedThreadPool(2);
        Future<String> f1 = exec.submit(() -> {
            log.info("i am {} begin", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(6);
            log.info("i am {} end", Thread.currentThread().getName());
            return "ok1";
        });
        Future<String> f2 = exec.submit(() -> {
            log.info("i am {} begin", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(2);
            log.info("i am {} end", Thread.currentThread().getName());
            return "ok2";
        });
        Future<String> f3 = exec.submit(() -> {
            log.info("i am {} begin", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
            log.info("i am {} end", Thread.currentThread().getName());
            return "ok2";
        });

        List<Future<String>> futureList = new ArrayList<>();
        futureList.add(f1);
        futureList.add(f2);
        futureList.add(f3);

        futureList.stream().forEach(f -> {
            try {
                log.info(f.get());
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        });
    }
}
