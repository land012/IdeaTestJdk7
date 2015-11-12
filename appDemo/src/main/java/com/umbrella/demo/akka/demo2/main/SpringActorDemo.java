package com.umbrella.demo.akka.demo2.main;

import akka.actor.ActorRef;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xudazhou on 2015/11/2.
 */
public class SpringActorDemo {

    private ActorRef goodmorningActorRef;

    public void setGoodmorningActorRef(ActorRef goodmorningActorRef) {
        this.goodmorningActorRef = goodmorningActorRef;
    }

    public SpringActorDemo() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config-akka.xml");
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    public static void main(String[] args) {
        SpringActorDemo springActorDemo = new SpringActorDemo();
        springActorDemo.goodmorningActorRef.tell(new Object(), ActorRef.noSender());
    }
}
