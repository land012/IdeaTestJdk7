package com.umbrella.demo.akka.concur;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2015/12/30.
 */
public class ListenerActor extends UntypedActor {

    private static final Logger log = LoggerFactory.getLogger(ListenerActor.class);

    private int i = -1;
    private int count = 0;

    @Override
    public void onReceive(Object o) throws Exception {
        if(o instanceof Integer) {
            Integer k = (Integer)o;
            if(k>i) i = k;
            count++;
            if(count==1000) {
                log.info("I am Listener, result=" + i); // I am Listener, result=999
                this.getContext().system().shutdown();
            }
        } else {
            unhandled(o);
        }
    }
}
