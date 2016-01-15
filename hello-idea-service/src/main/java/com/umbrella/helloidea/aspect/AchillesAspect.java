package com.umbrella.helloidea.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by xudazhou on 2016/1/15.
 */
@Component
@Aspect
public class AchillesAspect {

    private static final Logger log = LoggerFactory.getLogger(AchillesAspect.class);

    public AchillesAspect() {
        log.info("I am AchillesAspect");
    }

//    @Pointcut("execution(* com.umbrella.demo.spring.service..)") // 报错的写法
//    @Pointcut("execution(* com.umbrella.demo.spring.service..(..))") // 报错的写法
    @Pointcut("execution(* com.umbrella.demo.spring.service..*(..))")
    public void springService() {}

    @Before("springService()")
    public void before(JoinPoint joinPoint) {
        log.info("I am AchillesAspect, i aop " + joinPoint.getTarget().getClass().getName());
    }
}
