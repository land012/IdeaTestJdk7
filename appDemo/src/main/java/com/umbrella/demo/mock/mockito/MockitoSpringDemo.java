package com.umbrella.demo.mock.mockito;

import com.umbrella.demo.mock.subjects.dao.HelloDao;
import com.umbrella.demo.mock.subjects.manager.HelloManager;
import com.umbrella.demo.mock.subjects.service.HelloService;
import com.umbrella.vo.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by 大洲 on 15-1-4.
 */
//@RunWith(MockitoJUnitRunner.class) // Mockito 初始化
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config.xml" })
public class MockitoSpringDemo {

//    private static ApplicationContext applicationContext;

    @Resource
    @InjectMocks
    private HelloService helloService;

    @Resource
    @InjectMocks
    private HelloManager helloManager;

    @Spy
    private HelloDao helloDao;

    @BeforeClass
    public static void beforeClass() {
//        applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
    }

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        //        helloService = applicationContext.getBean("helloService", HelloService.class);
    }

    /**
     * 测试 Spring
     */
    @Test
    public void test0() {
        User u1 = this.helloService.getUser();
        System.out.println(u1.getUserName());
    }

    /**
     * @Spy 只代理指定 when 的方法，其它方法走默认
     */
    @Test
    public void test2() {
        User u2 = this.helloManager.getUser();
        System.out.println(u2.getUserName());
    }

    /**
     * Spring 整合 Mockito
     * 通过
     */
    @Test
    public void test1() {
        User u = new User();
        u.setId(20);
        u.setUserName("Alphonse");
        Mockito.when(this.helloDao.getUser()).thenReturn(u);
        User u1 = this.helloService.getUser();
        System.out.println(u1.getUserName());
    }

}
