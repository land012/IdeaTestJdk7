package com.umbrella.demo.spring;

import com.umbrella.demo.spring.service.YukimuraService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 大洲 on 15-5-28.
 * 编程注入对象
 */
public class Demo2 {

    private YukimuraService yukimuraService;

    public void setYukimuraService(YukimuraService yukimuraService) {
        this.yukimuraService = yukimuraService;
    }

    public Demo2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        new Thread(new Task(demo2.yukimuraService)).start();
        new Thread(new Task(demo2.yukimuraService)).start();
    }

    static class Task implements Runnable {

        private YukimuraService yukimuraService;

        Task(YukimuraService yukimuraService) {
            this.yukimuraService = yukimuraService;
        }

        @Override
        public void run() {
            this.yukimuraService.hello();
        }
    }
}
