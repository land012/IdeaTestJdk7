package com.umbrella.demo.forkjoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2016/3/31.
 * 实际 1*1 + 2*2 + ... + 5*5=55
 */
public class CalcTask extends RecursiveTask<Integer> {

    private static final Logger log = LoggerFactory.getLogger(CalcTask.class);

    private Integer i;

    public CalcTask(Integer i) {
        this.i = i;
    }

    @Override
    protected Integer compute() {
        if(i>100) return 0;
        try {
            log.info("I am " + Thread.currentThread().getName() + ",i=" + i);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            log.error("", e);
        }
        CalcTask calcTask = new CalcTask(i+1);
        calcTask.fork();
        return i*i + calcTask.join();
    }
}
