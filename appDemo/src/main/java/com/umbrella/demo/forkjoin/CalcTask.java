package com.umbrella.demo.forkjoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2016/3/31.
 * 计算 1*1 + 2*2 + ... + 5*5=55
 */
public class CalcTask extends RecursiveTask<Integer> {

    private static final Logger log = LoggerFactory.getLogger(CalcTask.class);

    private Integer startNum;
    private Integer maxNum;

    public CalcTask(Integer startNum) {
        this.startNum = startNum;
    }

    public CalcTask(Integer startNum, Integer maxNum) {
        this.startNum = startNum;
        this.maxNum = maxNum;
    }

    @Override
    protected Integer compute() {
        if(startNum >this.maxNum) return 0;
        try {
            log.info("I am " + Thread.currentThread().getName() + ",startNum=" + startNum);
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            log.error("", e);
        }
        CalcTask calcTask = new CalcTask(startNum +1, this.maxNum);
        calcTask.fork();
        return startNum * startNum + calcTask.join();
    }
}
