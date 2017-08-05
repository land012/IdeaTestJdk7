package com.umbrella.demo.java.time;

import org.junit.Test;

import java.time.ZoneId;

/**
 * Created by xudazhou on 2017/7/19.
 */
public class ZoneIdDemo {

    @Test
    public void test1() {
        ZoneId.getAvailableZoneIds()
                .forEach((zi) -> System.out.println(zi));
    }

    @Test
    public void test2() {
        System.out.println(ZoneId.systemDefault()); // Asia/Shanghai
    }
}
