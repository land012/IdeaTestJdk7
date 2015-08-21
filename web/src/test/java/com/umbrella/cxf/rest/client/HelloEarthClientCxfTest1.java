package com.umbrella.cxf.rest.client;

import com.umbrella.cxf.rest.service.HelloEarthService;
import com.umbrella.cxf.ws.server.HelloWorldService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xudazhou on 2015/8/14.
 */
public class HelloEarthClientCxfTest1 {
    private HelloEarthService helloEarthService;
    private HelloWorldService helloWorldService;

    public void setHelloEarthService(HelloEarthService helloEarthService) {
        this.helloEarthService = helloEarthService;
    }

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "spring-config-rest-client.xml" });
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    /**
     * 测试 CXF REST
     */
    @Test
    public void test1() {
        System.out.println(this.helloEarthService.getStudentById(1));
    }

    /**
     * 验证 Spring
     */
    @Test
    public void test2() {
        System.out.println(this.helloWorldService.sayHello("Hatake"));
    }
}
