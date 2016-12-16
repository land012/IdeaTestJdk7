package com.umbrella.demo.spring.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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
        log.info("I am SusanooAnno before, " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());

        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        SusanooAnno susanooAnno = method.getDeclaredAnnotation(SusanooAnno.class);
        log.info(susanooAnno.value());
    }

    /**
     * 另外一种写法
     */
    @Pointcut("@annotation(com.umbrella.demo.spring.annotation.SusanooAnno)")
    public void pointcut() {}

    @Before("pointcut()")
    public void before1(JoinPoint joinPoint) {
        log.info("I am SusanooAnno before1, " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
    }

}
