package com.umbrella.demo.spring;

import com.umbrella.demo.spring.factory.UserFactory;
import com.umbrella.demo.spring.factory.UserFactory3;
import com.umbrella.demo.spring.factory.UserFactoryBean;
import com.umbrella.demo.spring.factory.UserService;
import com.umbrella.demo.spring.service.LeahService;
import com.umbrella.demo.spring.util.MyPropertyFactoryBean;
import com.umbrella.vo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xudazhou on 2015/10/14.
 */
public class SpringFactoryTest {

    private ApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("spring/spring-config-factory.xml");
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    // 自动注入
    // 静态工厂
    private User user1;
    // 动态工厂
    private User user2;

    private UserService userService;

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Spring 静态工厂 和 实例工厂
     */
    @Test
    public void test2() {
        System.out.println("============ test2 begin =============================================================");
        System.out.println(user1);
        System.out.println(user2);
        System.out.println("============ test2 end =============================================================");
    }

    /**
     * 不同线程中的 User 是同一个实例
     */
    @Test
    public void test2_1() {
        System.out.println("============ test2_1 begin =============================================================");
        new Thread(() -> {
            this.userService.fn1();
        }).start();
        new Thread(() -> {
            this.userService.fn1();
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("============ test2_1 end =============================================================");
    }

    /**
     * 奇葩的用法
     * 工厂方法
     * @throws Exception
     * 使用 getInstance()，UserFactory 中的 static 成员变量可以注入
     * 原因：
     *     因为实际上有两个实例，使用了 static jvm只有一个实例的特性
     */
    @Test
    public void test5() throws Exception {
        UserFactory3 userFactory3 = UserFactory3.getInstance();
        userFactory3.hello();
    }

    /**
     * 实现 FactoryBean 的工厂
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        Object obj2 = context.getBean("user3");
        System.out.println(obj2 instanceof User); // true
        System.out.println(obj2 instanceof UserFactoryBean); // false
        new CountDownLatch(1).await();
    }

    /**
     * 不同线程中的 User 是同一个实例，内存地址相同
     */
    @Test
    public void test6_1() {
        System.out.println("============ test6_1 begin =============================================================");
        new Thread(() -> {
            this.userService.fn3();
        }).start();
        new Thread(() -> {
            this.userService.fn3();
        }).start();
        new Thread(() -> {
            this.userService.fn3();
        }).start();
        new Thread(() -> {
            this.userService.fn3();
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("============ test6_1 end =============================================================");
    }

    /**
     * 打印 UserFactoryBean 实例id
     */
    @Test
    public void test7() {
        System.out.println("===================== begin ===========================================");
        Map<String, UserFactoryBean> map = context.getBeansOfType(UserFactoryBean.class);
        for(Map.Entry<String, UserFactoryBean> entry : map.entrySet()) {
            System.out.println(String.format("%s - %s", entry.getKey(), entry.getValue())); // &user3 - com.umbrella.demo.spring.factory.UserFactoryBean@18aa41b
        }
        System.out.println("===================== end ===========================================");
    }
}
