package com.umbrella.cxf.rest.service;

import com.umbrella.ws.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xudazhou on 2015/8/14.
 */
public class HelloEarthServiceTest {

    private static final Logger log = LoggerFactory.getLogger(HelloEarthServiceTest.class);

    private HelloEarthService helloEarthService;

    public void setHelloEarthService(HelloEarthService helloEarthService) {
        this.helloEarthService = helloEarthService;
    }

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "spring-config.xml" });
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    @Test
    public void test1() {
        Student s1 = this.helloEarthService.getStudentById(2);
        log.info(s1.toString());
    }
}
