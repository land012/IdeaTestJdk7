package com.umbrella.demo.mock.mockito;

import com.umbrella.demo.mock.subjects.manager.HelloManager;
import com.umbrella.demo.mock.subjects.service.HelloService;
import com.umbrella.vo.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by 大洲 on 15-1-4.
 */
//@RunWith(MockitoJUnitRunner.class)
public class MockitoDemo2 {
    @InjectMocks
    private HelloService helloService;
    @Mock
    private HelloManager helloManager;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() {
        User u = new User();
        u.setId(20);
        u.setUserName("Alphonse");
        Mockito.when(this.helloManager.getUse2(Mockito.anyLong())).thenReturn(u);
        this.helloService.verifyUser2();
    }
}
