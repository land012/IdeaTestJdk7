package com.umbrella.demo.spring;

import com.umbrella.demo.spring.service.LeahService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * Created by xudazhou on 2015/10/14.
 */
public class SpringTest {

    @Resource
    private LeahService leahService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    /**
     * AOP
     */
    @Test
    public void test1() {
        System.out.println("============= test1 begin =========================================");
        this.leahService.hello("Leah Dizon");
        System.out.println("============= test1 end =========================================");
    }
}
