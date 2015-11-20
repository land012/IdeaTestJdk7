package com.umbrella.demo.spring;

import com.umbrella.common.service.AchillesService;
import com.umbrella.common.service.SasukeService;
import com.umbrella.demo.spring.factory.UserFactory;
import com.umbrella.demo.spring.factory.UserFactoryBean;
import com.umbrella.demo.spring.service.LeahService;
import com.umbrella.demo.spring.util.MyPropertyFactoryBean;
import com.umbrella.vo.User;
import org.junit.Before;
import org.junit.Test;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by xudazhou on 2015/10/14.
 */
public class SpringTest {

    private ApplicationContext context;

    @Resource
    private LeahService leahService;

    @Autowired
    private AchillesService achillesService;

    @Autowired
    private SasukeService sasukeService;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
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


    /**
     * 获取配置文件配置
     */
    @Test
    public void test3() {
        System.out.println("============ test3 begin ================================================");
        System.out.println(MyPropertyFactoryBean.getProperty("k1"));
        System.out.println("============ test3 end ================================================");
    }

    @Test
    public void test4() throws Exception {
        System.out.println("============ test4 begin ================================================");
        this.achillesService.hello();
//        this.sasukeService.hello();
        System.out.println("============ test4 end ================================================");
    }

}
