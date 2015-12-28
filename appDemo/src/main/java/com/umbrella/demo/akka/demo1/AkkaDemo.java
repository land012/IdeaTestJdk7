package com.umbrella.demo.akka.demo1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.umbrella.demo.akka.demo2.actor.GreeterActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by 大洲 on 15-4-23.
 */
public class AkkaDemo {

    private static final Logger log = LoggerFactory.getLogger(AkkaDemo.class);

    public static void main(String[] args) {
        // 用这种方式启动 不能使用 getContext.stop() 退出
//        ActorSystem system = ActorSystem.create("actorsystem");
//        Props greetProps = Props.create(LeahActor.class); // 实例化一个Actor,该方法的其它参数是 Actor的构造方法的参数
//        final ActorRef leahActor = system.actorOf(greetProps, "leahActor"); // 第二个参数是 actor的名字
//        log.info(leahActor.path().toString()); // akka://actorsystem/user/leahActor

        /*
         * 用这种方式启动 可以使用 getContext.stop() 退出
         * 但是必须在 LeahActor 里调用 getContext.stop()，在 DizonActor 里调用 getContext.stop() 是不会退出的
          */
        akka.Main.main(new String[]{ LeahActor.class.getName() });
    }
}
