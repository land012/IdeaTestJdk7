package com.umbrella.service;

import org.springframework.stereotype.Service;

/**
 * Created by 大洲 on 14-12-25.
 * 验证 互相调用，使用 Spring 注入会不会死循环？
 * 不会
 */
@Service
public class HelloWorldService {
    // 互相调用，不会死循环
    private HelloEarthService helloEarthService;

    public HelloWorldService() {
        System.out.println("this is HelloWorldService()");
    }

    public void sayHello() {
        System.out.println("Hello World Service");
    }

    public void setHelloEarthService(HelloEarthService helloEarthService) {
        this.helloEarthService = helloEarthService;
    }
}
