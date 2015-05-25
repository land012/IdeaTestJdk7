package cc.umbrella.ws.client.hello;

import com.umbrella.ws.client.Hello;
import com.umbrella.ws.client.HelloService;

/**
 * Created by 大洲 on 14-11-18.
 */
public class Client {
    public static void main(String[] args) {
        Hello hello = new HelloService().getHelloPort();
        System.out.println(hello.sayHello("Tom"));
    }
}
