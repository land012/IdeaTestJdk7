package com.umbrella.demo.akka.demo2.main;

import akka.actor.ActorRef;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 尝试整合 Spring
 * Created by xudazhou on 2015/11/2.
 *
 * 仍然不能将 bean 注入到 Actor，因为 Actor 是 ActorSystem new 出来的，不是 Spring new 出来的
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
