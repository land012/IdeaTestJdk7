package com.umbrella.ws;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * Created by 大洲 on 14-11-18.
 * JDK 6 Web Service DEMO
 */
@WebService
public class Hello {
    public String sayHello(String name) {
        return "Hello " + name;
    }

    public static void main(String[] args) {
        String address = "http://localhost:8080/com.umbrella.ws.Hello";
        Endpoint.publish(address, new Hello());
    }
}
