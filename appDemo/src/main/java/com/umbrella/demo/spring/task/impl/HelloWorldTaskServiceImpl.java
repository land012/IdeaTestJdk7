package com.umbrella.demo.spring.task.impl;

import com.umbrella.demo.spring.task.HelloWorldTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2015/11/10.
 */
public class HelloWorldTaskServiceImpl implements HelloWorldTaskService {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldTaskServiceImpl.class);

    @Override
    public void execute() {
        log.info("================== begin I am " + Thread.currentThread().getName());
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            log.error("", e);
        }
        log.info("================== end I am " + Thread.currentThread().getName());
    }
}
