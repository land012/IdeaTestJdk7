package com.umbrella.demo.akka.demo1;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2015/12/26.
 */
public class DizonActor extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(DizonActor.class);

    @Override
    public void onReceive(Object o) throws Exception {
        log.info("I am DizonActor");
        this.getSender().tell(new Object(), getSelf());
    }
}
