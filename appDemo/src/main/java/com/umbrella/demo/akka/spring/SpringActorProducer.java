package com.umbrella.demo.akka.spring;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;

/**
 * Created by xudazhou on 2015/12/25.
 */
public class SpringActorProducer implements IndirectActorProducer {

    private ApplicationContext applicationContext;
    private String actorBeanName;

    public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName) {
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
    }

    @Override
    public Actor produce() {
        return this.applicationContext.getBean(actorBeanName, Actor.class);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>)this.applicationContext.getType(actorBeanName);
    }
}
