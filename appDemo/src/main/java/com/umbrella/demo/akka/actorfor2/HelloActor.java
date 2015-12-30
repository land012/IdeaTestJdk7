package com.umbrella.demo.akka.actorfor2;

import akka.actor.Props;
import akka.actor.UntypedActor;
import org.apache.log4j.Logger;

/**
 * Created by 大洲 on 15-1-16.
 */
public class HelloActor extends UntypedActor {

    private static Logger log = Logger.getLogger(HelloActor.class);

    @Override
    public void preStart() throws Exception {
        this.getContext().actorOf(Props.create(Hello2Actor.class), "hello2actor");
        this.getContext().actorOf(Props.create(Hello3Actor.class), "hello3actor");
    }

    @Override
    public void onReceive(Object o) throws Exception {
        if(o== Msg.HELLO) {
            log.info("Hello World");
            getContext().stop(getSelf());
        } else {
            unhandled(o);
        }
    }

    static class Hello2Actor extends UntypedActor {

        @Override
        public void onReceive(Object o) throws Exception {
            if(o==Msg.HELLO) {
                log.info("hello2 world");
            }
        }
    }

    static class Hello3Actor extends UntypedActor {

        @Override
        public void onReceive(Object o) throws Exception {
            if(o==Msg.HELLO) {
                log.info("hello3 world");
            }
        }
    }
}
