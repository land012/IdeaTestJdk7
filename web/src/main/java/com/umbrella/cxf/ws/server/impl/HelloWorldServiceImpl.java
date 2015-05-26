package com.umbrella.cxf.ws.server.impl;

import com.umbrella.cxf.ws.server.HelloWorldService;
import com.umbrella.ws.model.User;
import com.umbrella.ws.model.Users;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 大洲 on 14-11-20.
 */
@WebService
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public String sayHello(String name) {
        String res = "Hello " + name;
        System.out.println(res);
        return res;
    }

    @Override
    public User getUser(String name) {
        User u = new User(name, 18);
        System.out.println(u);
        return u;
    }

    @Override
    public Users getUserList() {
        System.out.println("This is getUserList!");
        List<User> list = new ArrayList<User>();
        list.add(new User("Tom", 18));
        list.add(new User("Jim", 19));
        Users users = new Users();
        users.setUserList(list);
        return users;
    }
}
