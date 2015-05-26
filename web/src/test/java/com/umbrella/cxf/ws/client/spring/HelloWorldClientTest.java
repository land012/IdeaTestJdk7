package com.umbrella.cxf.ws.client.spring;

import com.umbrella.cxf.ws.server.HelloWorldService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 大洲 on 14-11-22.
 */
public class HelloWorldClientTest {
    private static ApplicationContext context;
    private static HelloWorldService helloWorldService;

    @BeforeClass
    public static void beforeClass() {
        context = new ClassPathXmlApplicationContext("spring-config-ws-client.xml");
    }

    @Test
    public void test1() {
        helloWorldService = (HelloWorldService)context.getBean("helloWorldClient");
        System.out.println(helloWorldService.sayHello("Tom"));
    }
}
