package com.umbrella.demo.spring;

import com.umbrella.demo.spring.service.YukimuraService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 大洲 on 15-5-28.
 * 编程注入对象
 * Spring 容器里只有一个 yukimuraService 实例
 */
public class SpringDemo {

    private YukimuraService yukimuraService;

    public void setYukimuraService(YukimuraService yukimuraService) {
        this.yukimuraService = yukimuraService;
    }

    public SpringDemo() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    public static void main(String[] args) {
        SpringDemo demo2 = new SpringDemo();
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
