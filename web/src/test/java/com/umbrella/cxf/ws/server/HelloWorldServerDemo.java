package com.umbrella.cxf.ws.server;

import com.umbrella.cxf.interceptor.MyInInterceptor;
import com.umbrella.cxf.ws.server.impl.HelloWorldServiceImpl;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.phase.Phase;

/**
 * Created by 大洲 on 14-11-22.
 * 不使用Spring
 * http://localhost:9999/ws/helloWorld?wsdl
 */
public class HelloWorldServerDemo {
    public static void main(String[] args) {
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
//        factory.getInInterceptors().add(new LoggingInInterceptor());
//        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.getInInterceptors().add(new MyInInterceptor(Phase.RECEIVE));
        factory.setServiceClass(HelloWorldService.class);
        factory.setAddress("http://localhost:9991/ws/helloWorld");
        factory.setServiceBean(new HelloWorldServiceImpl());
        factory.create();
    }
}
