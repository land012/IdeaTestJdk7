package com.umbrella.demo.java.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by xudazhou on 2016/4/26.
 */
public class UUIDDemo {

    private static final Logger log = LoggerFactory.getLogger(UUIDDemo.class);

    @Test
    public void test1() {
        log.info(UUID.randomUUID().toString());
    }
}
