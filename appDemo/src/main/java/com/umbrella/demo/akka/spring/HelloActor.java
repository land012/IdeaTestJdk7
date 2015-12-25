package com.umbrella.demo.akka.spring;

import akka.actor.UntypedActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by xudazhou on 2015/12/25.
 */
@Component("helloActor")
@Scope("prototype")
public class HelloActor extends UntypedActor {

    @Autowired
    private HelloService helloService;

    // 构造函数注入
//    @Autowired
//    public HelloActor(@Qualifier("helloService")HelloService helloService) {
//        this.helloService = helloService;
//    }

    @Override
    public void onReceive(Object o) throws Exception {
        this.helloService.say();
    }
}
