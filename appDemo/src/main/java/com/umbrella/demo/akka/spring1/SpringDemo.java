package com.umbrella.demo.akka.spring1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 整合Spring
 * Created by xudazhou on 2015/12/25.
 * 整合成功
 */
public class SpringDemo {

    @Autowired
    private ActorSystem actorSystem001;

    public static void main(String[] args) {
        SpringDemo springDemo = new SpringDemo();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config-akka.xml");
        ctx.getAutowireCapableBeanFactory().autowireBeanProperties(springDemo, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
        SpringExtensionId springExtensionId = SpringExtensionId.getInstance(ctx);

        ActorRef helloActor = springDemo.actorSystem001.actorOf(springExtensionId.props("helloActor"), "hello");
        helloActor.tell(new Object(), ActorRef.noSender());
        springDemo.actorSystem001.shutdown();
    }
}
