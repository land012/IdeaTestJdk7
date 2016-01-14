package com.umbrella.demo.spring;

import com.umbrella.common.service.AchillesService;
import com.umbrella.common.service.SasukeService;
import com.umbrella.demo.spring.service.LeahService;
import com.umbrella.demo.spring.util.MyPropertyFactoryBean;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    public void test1() {
        System.out.println("============= test1 begin =========================================");
    }

}
