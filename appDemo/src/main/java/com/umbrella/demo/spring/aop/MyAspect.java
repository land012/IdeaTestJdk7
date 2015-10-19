package com.umbrella.demo.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2015/10/14.
 */
public class MyAspect {

    private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

    public void doBefore(JoinPoint jp) {
        log.info("begining methd:" + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + "()");
    }

    public void doAfter(JoinPoint jp) {
        log.info("ending methd:" + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + "()");
    }
}
