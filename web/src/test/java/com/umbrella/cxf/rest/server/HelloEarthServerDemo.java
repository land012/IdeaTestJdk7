package com.umbrella.cxf.rest.server;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.umbrella.cxf.interceptor.MyInInterceptor;
import com.umbrella.cxf.rest.service.impl.HelloEarthServiceImpl;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.phase.Phase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 大洲 on 14-11-23.
 * REST 方式
 */
public class HelloEarthServerDemo {
    public static void main(String[] args) {
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
//        factory.getInInterceptors().add(new LoggingInInterceptor());
        factory.getInInterceptors().add(new MyInInterceptor(Phase.RECEIVE));
//        factory.getOutInterceptors().add(new LoggingOutInterceptor());
        factory.setResourceClasses(HelloEarthServiceImpl.class);
        List<Object> providers = new ArrayList<>();
        providers.add(new JacksonJsonProvider());
        factory.setProviders(providers);
        factory.setAddress("http://localhost:8080/rs");
        factory.create();
    }
}
