package com.umbrella.grammar.proxy;

import com.umbrella.grammar.proxy.handler.WrapHandler;
import com.umbrella.grammar.proxy.service.UserService;
import com.umbrella.grammar.proxy.service.UserServiceImpl;
import com.umbrella.vo.User;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by 大洲 on 15-1-7.
 */
public class ProxyDemo {

    /**
     * 产生代理类
     */
    @Test
    public void test1() {
        UserService userService = new UserServiceImpl();
        WrapHandler handler = new WrapHandler(userService);
        UserService userServiceProxy = (UserService)Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(),
                handler);
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("Oda Nobunaga");
        userServiceProxy.save(u1);
    }

    /**
     * 直接调用 Handler
     * @throws Throwable
     */
    @Test
    public void test2() throws Throwable {
        UserService userService = new UserServiceImpl();
        WrapHandler handler = new WrapHandler(userService);
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("Orochimaru");
        Method method = userService.getClass().getDeclaredMethod("save", User.class);
        handler.invoke(userService, method, new Object[]{ u1 });
    }
}
