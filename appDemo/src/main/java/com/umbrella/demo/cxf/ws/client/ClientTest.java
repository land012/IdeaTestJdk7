package com.umbrella.demo.cxf.ws.client;

import com.umbrella.cxf.ws.server.HelloWorldService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
//import org.apache.cxf.phase.Phase;
import org.junit.Test;

/**
 * Created by 大洲 on 15-4-9.
 */
public class ClientTest {
    /**
     * 接口路径需与服务端一致
     */
    @Test
    public void test1() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//        factory.getOutInterceptors().add(new MyOutInterceptor(Phase.SEND));
        factory.setServiceClass(HelloWorldService.class);
        factory.setAddress("http://localhost:9999/ws/helloWorld");

        HelloWorldService client = (HelloWorldService)factory.create();

        // 返回 String
        System.out.println(client.sayHello("Jim"));

    }
}
