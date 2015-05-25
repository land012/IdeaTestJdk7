package com.umbrella.demo.akka.demo2.actor;

import akka.actor.*;
import com.umbrella.demo.akka.demo2.Msg;
import org.apache.log4j.Logger;


/**
 * Created by 大洲 on 15-1-16.
 */
public class GreeterActor extends UntypedActor {

    private static Logger log = Logger.getLogger(GreeterActor.class);

    @Override
    public void onReceive(Object o) throws Exception {
        if(o== Msg.GREET) {
            log.info("Greet World!");
            // 判断空
//            ActorRef helloActor = this.getContext().getChild("helloactor");
//            if(helloActor==null) {
//                log.info("cannot find helloactor, created!");
//                helloActor = this.getContext().actorOf(Props.create(HelloActor.class), "helloactor");
//            } else {
//                log.info("find helloactor");
//            }

            // 判断空2
            ActorRef helloActor = this.getContext().actorFor("akka://AS001/user/greetactor/helloactor");
            if(helloActor instanceof EmptyLocalActorRef) {
                log.info("cannot find helloactor, created!");
                helloActor = this.getContext().actorOf(Props.create(HelloActor.class), "helloactor");
            } else {
                log.info("find helloactor");
            }

            log.info(helloActor.path()); // akka://actorsystem/user/greetactor/helloactor

//            // 判断空3 TODO
//            ActorPath helloPath = this.getContext().
//            ActorSelection helloActor = this.getContext().actorSelection("helloactor");
//            if(helloActor==null) {
//                log.info("cannot find helloactor, created!");
//                ActorRef helloRef = this.getContext().actorOf(Props.create(HelloActor.class), "helloactor");
//                log.info(helloRef.path());
//            } else {
//                log.info("find helloactor");
//                log.info(helloActor.path());
//            }

//            getSender().tell(Msg.DONE, getSelf());
        } else if(o==Msg.HELLO) {

        } else {
            unhandled(o);
        }
    }
}
