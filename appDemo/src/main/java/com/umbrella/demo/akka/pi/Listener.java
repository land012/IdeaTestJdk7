package com.umbrella.demo.akka.pi;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2015/12/29.
 */
public class Listener extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    @Override
    public void onReceive(Object o) throws Exception {
        log.info("this is listener");
        if(o instanceof PiApproximation) {
            PiApproximation approximation = (PiApproximation)o;
            log.info(String.format("pi:%s,cost:%s\n", approximation.getPi(), approximation.getDuration()));
            this.getContext().system().shutdown();
        } else {
            this.unhandled(o);
        }
    }
}
