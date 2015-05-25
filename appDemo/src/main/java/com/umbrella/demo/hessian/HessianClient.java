package com.umbrella.demo.hessian;

import com.caucho.hessian.client.HessianProxyFactory;
import com.umbrella.demo.hessian.server.HelloService;

import java.net.MalformedURLException;

/**
 * Created by 大洲 on 14-12-19.
 * Hessian 客户端，服务端在 模块springweb 中
 */
public class HessianClient {
    public static void main(String[] args) {
        try {
            String url = "http://localhost:9999/rs/helloService";
            HessianProxyFactory factory = new HessianProxyFactory();
            /*
             * 创建接口的代理类
             */
            HelloService service = (HelloService)factory.create(HelloService.class, url);
            System.out.println(service.sayHello("Alphonse"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
