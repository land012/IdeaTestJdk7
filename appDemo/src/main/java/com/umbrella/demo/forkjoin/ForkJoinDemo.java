package com.umbrella.demo.forkjoin;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Created by xudazhou on 2016/3/31.
 */
public class ForkJoinDemo {

    private static final Logger log = LoggerFactory.getLogger(ForkJoinDemo.class);

    @Test
    public void test1() {
        ForkJoinPool fjPool = new ForkJoinPool();
        log.info(fjPool.invoke(new CalcTask(1, 5)) + "");
        fjPool.shutdown();
        log.info("I am main test end");
    }

    @Test
    public void test2() throws Exception {
        ForkJoinPool fjPool = new ForkJoinPool();
        Integer[] arr1 = { 7, 3, 5, 22, 67, 21, 20, 15, 99, 15, 34, 33, 4, 6, 1, 2, 12 };
        Future<Integer[]> f = fjPool.submit(new QuickSortTask(arr1, 0, arr1.length-1));
        Integer[] arr2 = f.get();
        log.info(Arrays.toString(arr2));
    }
}
