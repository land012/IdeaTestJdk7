package com.umbrella.demo.akka.spring;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;

/**
 * Created by xudazhou on 2015/12/25.
 */
public class SpringExtensionId extends AbstractExtensionId<SpringExtension> {

    private static SpringExtensionId instance;
    private static ApplicationContext applicationContext;

    private SpringExtensionId(){}

    public static SpringExtensionId getInstance(ApplicationContext context) {
        if(instance==null) {
            synchronized (SpringExtensionId.class) {
                if(instance==null) {
                    instance = new SpringExtensionId();
                    applicationContext = context;
                }
            }
        }
        return instance;
    }

    public Props props(String actorBeanName) {
        return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
    }

    @Override
    public SpringExtension createExtension(ExtendedActorSystem extendedActorSystem) {
        return new SpringExtension();
    }

}
