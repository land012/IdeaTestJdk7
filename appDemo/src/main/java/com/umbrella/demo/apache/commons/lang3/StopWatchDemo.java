package com.umbrella.demo.apache.commons.lang3;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2017/1/6.
 */
public class StopWatchDemo {
    @Test
    public void test1() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTime()); // 3001 毫秒
    }
}
