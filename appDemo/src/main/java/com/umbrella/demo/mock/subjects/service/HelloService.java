package com.umbrella.demo.mock.subjects.service;

import com.umbrella.demo.mock.subjects.manager.HelloManager;
import com.umbrella.vo.User;
import org.springframework.stereotype.Component;

/**
 * Created by 大洲 on 15-1-4.
 */
@Component
public class HelloService {
    private HelloManager helloManager;

    public void setHelloManager(HelloManager helloManager) {
        this.helloManager = helloManager;
    }

    public User getUser() {
        return helloManager.getUser();
    }

    public void verifyUser2() {
        User u = this.helloManager.getUse2(2);
        System.out.println(u.getUserName());
    }
}
