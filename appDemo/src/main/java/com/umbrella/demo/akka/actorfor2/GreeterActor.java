package com.umbrella.demo.akka.actorfor2;

import akka.actor.*;
import org.apache.log4j.Logger;


/**
 * Created by 大洲 on 15-1-16.
 */
public class GreeterActor extends UntypedActor {

    private static Logger log = Logger.getLogger(GreeterActor.class);

    @Override
    public void preStart() throws Exception {
        log.info("I am GreeterActor preStart");
    }

    @Override
    public void onReceive(Object o) throws Exception {
        if(o== Msg.GREET) {
            log.info("Greet World!");
            // 判断空
//            ActorRef helloActor = this.getContext().getChild("helloActor");
//            if(helloActor==null) {
//                log.info("cannot find helloactor, created!");
//                helloActor = this.getContext().actorOf(Props.create(HelloActor.class), "helloActor");
//            } else {
//                log.info("find helloactor");
//            }

            /**
             * 可以找到 HelloActor
             */
            ActorRef helloActor = this.getContext().actorFor("akka://actorsystem/user/helloActor");
            if(helloActor instanceof EmptyLocalActorRef) {
                log.info("cannot find helloactor, created!");
                helloActor = this.getContext().actorOf(Props.create(HelloActor.class), "helloActor");
            } else {
                log.info("find helloactor");
            }

            log.info(helloActor.path()); // akka://actorsystem/user/helloActor

//            // 判断空3 TODO
//            ActorPath helloPath = this.getContext().
//            ActorSelection helloActor = this.getContext().actorSelection("helloActor");
//            if(helloActor==null) {
//                log.info("cannot find helloactor, created!");
//                ActorRef helloRef = this.getContext().actorOf(Props.create(HelloActor.class), "helloActor");
//                log.info(helloRef.path());
//            } else {
//                log.info("find helloactor");
//                log.info(helloActor.path());
//            }

//            getSender().tell(Msg.DONE, getSelf());
        } else if(o==Msg.HELLO) {

        } else {
            log.info("Hello GreeterActor");
            unhandled(o);
        }
    }
}
