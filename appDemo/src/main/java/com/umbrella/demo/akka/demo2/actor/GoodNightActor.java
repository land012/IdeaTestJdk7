package com.umbrella.demo.akka.demo2.actor;

import akka.actor.UntypedActor;
import org.apache.log4j.Logger;

/**
 * Created by 大洲 on 15-4-23.
 */
public class GoodNightActor extends UntypedActor {

    private static final Logger log = Logger.getLogger(GoodNightActor.class);

    @Override
    public void onReceive(Object o) throws Exception {
        log.info("Good Nigth");
        this.getContext().stop(this.getSelf());
    }
}
