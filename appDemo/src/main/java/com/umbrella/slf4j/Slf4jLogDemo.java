package com.umbrella.slf4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * create by xudazhou 2017/12/19
 */
public class Slf4jLogDemo {

    public static final Logger log = LoggerFactory.getLogger(Slf4jLogDemo.class);

    @Test
    public void test1() {
        log.info("i am {}", "lilei");
    }

    @Test
    public void test2() {
        String[] arr1 = { "a", "b", "c" };
        log.info("log is {}", arr1); // log is a
        log.info("log is {} {}", arr1); // log is a b
        log.info("log is {} {} {}", arr1); // log is a b c
        log.info("log is {}", Arrays.toString(arr1)); // log is [a, b]
    }

    @Test
    public void test3() {
        String[] arr1 = { "a", "b" };
        log.info("log is {}, {}", arr1, "haha"); // log is [a, b], haha

        // 没有这个方法，会报错
//        log.info("{} {} {}", "a", "b", "c");
    }

    /**
     * 不会打印异常栈
     */
    @Test
    public void test4() {
        try {
            int i1 = Integer.parseInt("s");
        } catch (Exception e) {
            log.info("msg={}, ex={}", "exeeeeee", e);
        }
    }

    /**
     * 会打印异常栈
     */
    @Test
    public void test5() {
        try {
            int i1 = Integer.parseInt("s");
        } catch (Exception e) {
            log.info("msg={}, ex=", "exeeeeee", e);
        }
    }
}
