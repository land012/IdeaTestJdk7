package com.umbrella.cxf.rest.client;

import com.umbrella.cxf.interceptor.MyOutInterceptor;
import com.umbrella.cxf.rest.service.HelloEarthService;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.phase.Phase;
import org.junit.Test;

/**
 * Created by ´óÖÞ on 14-11-23.
 * REST ¿Í»§¶Ë
 */
public class HelloEarthClientTest2 {
    @Test
    public void test1() {
        JAXRSClientFactoryBean factory = new JAXRSClientFactoryBean();
        factory.getOutInterceptors().add(new MyOutInterceptor(Phase.SEND));
        factory.setServiceClass(HelloEarthService.class);
        factory.setAddress("http://localhost:8080/rs");
        HelloEarthService service = (HelloEarthService)factory.create();
        System.out.println(service.getStudentById(1));
    }

}
