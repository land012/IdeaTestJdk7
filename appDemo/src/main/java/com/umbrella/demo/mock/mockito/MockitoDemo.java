package com.umbrella.demo.mock.mockito;

import com.umbrella.demo.mock.subjects.dao.HelloDao;
import com.umbrella.demo.mock.subjects.manager.HelloManager;
import com.umbrella.demo.mock.subjects.service.HelloService;
import com.umbrella.vo.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 大洲 on 15-1-4.
 */
//@RunWith(MockitoJUnitRunner.class)
public class MockitoDemo {
    @InjectMocks
    private HelloService helloService;
    @InjectMocks
    private HelloManager helloManager;
    @Mock
    private HelloDao helloDao;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * verify
     */
    @Test
    public void test0() {
        List list1 = Mockito.mock(List.class);

        /*
         * list1 添加记录“one”“two”，然后清空 list1
         */
        list1.add("one");
        list1.add("two");
        list1.clear();

        // 验证通过
        Mockito.verify(list1).add("one");

        /*
          * 验证 list1 是否添加过 "three"
          * 结果：验证失败
          */
//        Mockito.verify(list1).add("three");
    }

    @Test
    public void test1() {
        List list1 = Mockito.mock(ArrayList.class);
        Mockito.when(list1.get(0)).thenReturn("first");
        System.out.println(list1.get(0));
    }

    @Test
    public void test2() {
        User u1 = new User();
        u1.setId(2);
        u1.setUserName("Alphonse");
        Mockito.when(helloDao.getUser()).thenReturn(u1);
        System.out.println(u1.getUserName());
    }

    /**
     * Mock Dao 注入到 Manager
     * 通过
     */
    @Test
    public void test3() {
        User u1 = new User();
        u1.setId(2);
        u1.setUserName("Alphonse");
        Mockito.when(helloDao.getUser()).thenReturn(u1);
        User u2 = helloManager.getUser();
        System.out.println(u2.getUserName()); // Alphonse
    }

    /**
     * Mock Dao，Manager,Service 加注入
     * Manager 不会自动注入到 Service
     * 异常
     */
    @Test
    public void test4() {
        User u1 = new User();
        u1.setId(2);
        u1.setUserName("Alphonse");
        Mockito.when(helloDao.getUser()).thenReturn(u1);
        User u2 = helloService.getUser();
        System.out.println(u2.getUserName());
    }

    /**
     * Mock Dao，Manager 加注入
     * Service 手动set Manager
     * 通过
     */
    @Test
    public void test5() {
        User u1 = new User();
        u1.setId(2);
        u1.setUserName("Alphonse");
        Mockito.when(helloDao.getUser()).thenReturn(u1);
        helloService.setHelloManager(helloManager);
        User u2 = helloService.getUser();
        System.out.println(u2.getUserName()); // Alphonse
    }

    @Test
    public void test6() {

    }
}
