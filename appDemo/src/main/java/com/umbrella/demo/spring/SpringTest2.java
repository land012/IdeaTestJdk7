package com.umbrella.demo.spring;

import com.umbrella.demo.spring.service.LeahService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * Created by xudazhou on 2015/10/14.
 * 验证 component-scan 扫描多个包
 */
public class SpringTest2 {

    private ApplicationContext context;

    @Resource
    private LeahService leahService;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("spring-config3.xml");
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    /**
     * 会扫描多个包路径
     * 会扫描其它模块下的路径
     * 会扫描 jar 下的路径
     */
    @Test
    public void test1() {
        System.out.println("============= test1 begin =========================================");
        leahService.hello("tom");

        System.out.println("============= test1 end =========================================");
    }

}
