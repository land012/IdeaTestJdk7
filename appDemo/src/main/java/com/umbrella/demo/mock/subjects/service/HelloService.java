package com.umbrella.demo.mock.subjects.service;

import com.umbrella.demo.mock.subjects.manager.HelloManager;
import com.umbrella.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by 大洲 on 15-1-4.
 */
@Service("helloService")
public class HelloService {

    private static final Logger log = LoggerFactory.getLogger(HelloService.class);

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

    public void tellSusanoo() {
        log.info(SusanooService.hello("jim"));
    }
}
