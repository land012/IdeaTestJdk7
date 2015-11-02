package com.umbrella.demo.spring;

import com.umbrella.demo.spring.service.LeahService;
import com.umbrella.vo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * Created by xudazhou on 2015/10/14.
 */
public class SpringTest {

    @Resource
    private LeahService leahService;

    @Before
    public void before() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    /**
     * AOP
     */
    @Test
    public void test1() {
        System.out.println("============= test1 begin =========================================");
        this.leahService.hello("Leah Dizon");
        System.out.println("============= test1 end =========================================");
    }


    private User user1;
    private User user2;

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    @Test
    public void test2() {
        System.out.println("============ test2 begin =============================================================");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println("============ test2 end =============================================================");
    }
}
