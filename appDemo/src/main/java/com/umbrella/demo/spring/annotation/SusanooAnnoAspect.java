package com.umbrella.demo.spring.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by xudazhou on 2015/11/20.
 */
@Aspect
@Component
public class SusanooAnnoAspect {

    private static final Logger log = LoggerFactory.getLogger(SusanooAnnoAspect.class);

    public SusanooAnnoAspect() {
//        log.info("I am SusanooAnnoAspect");
    }

    @Before("@annotation(com.umbrella.demo.spring.annotation.SusanooAnno)")
    public void before(JoinPoint joinPoint) {
        log.info("I am SusanooAnno, " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
    }

}
