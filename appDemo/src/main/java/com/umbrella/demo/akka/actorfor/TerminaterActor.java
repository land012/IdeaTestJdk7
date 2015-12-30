package com.umbrella.demo.akka.actorfor;

import akka.actor.UntypedActor;

/**
 * Created by xudazhou on 2015/12/30.
 */
public class TerminaterActor extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {
        this.getContext().system().shutdown();
    }
}
