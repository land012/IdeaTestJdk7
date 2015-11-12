package com.umbrella.demo.akka.demo2.main;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.umbrella.demo.akka.demo2.actor.GreeterActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by 大洲 on 15-4-23.
 */
public class GreetDemo {

    private static final Logger log = LoggerFactory.getLogger(GreetDemo.class);

    public static void main(String[] args) {
        /**
         * GreetActor
         */
        ActorSystem system = ActorSystem.create("actorsystem");
        Props greetProps = Props.create(GreeterActor.class); // 实例化一个Actor,该方法的其它参数是 Actor的构造方法的参数
        final ActorRef greetActor = system.actorOf(greetProps, "greetactor"); // 第二个参数是 actor的名字
        log.info(greetActor.path().toString()); // akka://actorsystem/user/greetactor
//
////        final ActorRef helloActor = system.actorOf(Props.create(HelloActor.class), "hello");
////        greetActor.tell(Msg.GREET, helloActor);
//        greetActor.tell(Msg.GREET, ActorRef.noSender());
//
//        GreetService greetService = new GreetService();
//        greetService.setActorSystem(system);
//        greetService.fn1();
    }
}
