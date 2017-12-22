package com.umbrella.slf4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by xudazhou 2017/12/19
 */
public class Slf4jLogDemo {

    public static final Logger log = LoggerFactory.getLogger(Slf4jLogDemo.class);

    @Test
    public void test1() {
        log.info("i am {}", "lilei");
    }
}
