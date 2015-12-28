package com.umbrella.demo.akka.concur;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2015/12/26.
 */
public class HelloActor extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(HelloActor.class);

    @Override
    public void onReceive(Object o) throws Exception {
        if(o instanceof MyCounter) {
            MyCounter counter = (MyCounter)o;
            counter.incr();
            log.info("I am HelloActor, msg is " + o);
        } else {
            unhandled(o);
        }
    }
}
