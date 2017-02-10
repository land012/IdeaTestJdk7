package com.umbrella.demo.mock.powermock;

import com.umbrella.demo.mock.subjects.service.HelloService;
import com.umbrella.demo.mock.subjects.service.BradleyService;
import com.umbrella.demo.mock.subjects.service.SusanooService;
import com.umbrella.demo.spring.SpringConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

/**
 * Created by xudazhou on 2016/12/28.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ SusanooService.class })
public class PowerMockDemo {
    @InjectMocks
    private HelloService helloService;

    @Autowired
    private BradleyService bradleyService;

    @Before
    public void before() {
        // 两种方式都可以拿到 ApplicationContext
//        ApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{ "spring-config.xml" });
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        ctx.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);

        PowerMockito.mockStatic(SusanooService.class);
        PowerMockito.when(SusanooService.hello(Mockito.anyString()))
                .thenReturn("Hello Susanoo");

    }

    /**
     * mock static 方法
     * 入门
     */
    @Test
    public void test1() {
        System.out.println(SusanooService.hello("haha")); // Hello Susanoo
    }

    /**
     * HelloService 本身就是 class
     * mock 类内部的静态方法
     * 通过
     */
    @Test
    public void test2() {
        this.helloService.tellSusanoo();
    }

    /**
     * BradleyService 是接口
     * 测试通过 符合预期
     *
     */
    @Test
    public void test3() {
        this.bradleyService.hello();
    }
}
