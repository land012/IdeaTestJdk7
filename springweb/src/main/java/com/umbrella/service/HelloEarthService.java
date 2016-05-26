package com.umbrella.service;

import org.springframework.stereotype.Service;

/**
 * Created by 大洲 on 14-12-25.
 * 验证 互相调用，使用 Spring 注入会不会死循环？
 * 不会
 */
@Service
public class HelloEarthService {
    private HelloWorldService helloWorldService;

    public HelloEarthService() {
        System.out.println("this is HelloEarthService()");
    }

    public void sayHello() {
        System.out.println("Hello Earth Service");
    }

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }
}
