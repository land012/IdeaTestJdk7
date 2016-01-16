package com.umbrella.demo.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2016/1/15.
 */
public class TimeUnitDemo {
    /**
     * 时间转换
     * 120 秒 可以转换为 多少分钟
     */
    @Test
    public void test1() {
        System.out.println(TimeUnit.MINUTES.convert(120, TimeUnit.SECONDS)); // 2
        System.out.println(TimeUnit.MINUTES.convert(119, TimeUnit.SECONDS)); // 1
        System.out.println(TimeUnit.MINUTES.convert(121, TimeUnit.SECONDS)); // 2

        System.out.println(TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES)); // 60
    }
}
