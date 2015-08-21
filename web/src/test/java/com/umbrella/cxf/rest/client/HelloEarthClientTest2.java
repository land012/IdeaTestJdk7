package com.umbrella.cxf.rest.client;

import com.umbrella.cxf.interceptor.MyOutInterceptor;
import com.umbrella.cxf.rest.service.HelloEarthService;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.apache.cxf.phase.Phase;
import org.junit.Test;

/**
 * Created by 大洲 on 14-11-23.
 * 编码 实现 REST 客户端
 */
public class HelloEarthClientTest2 {
    @Test
    public void test1() {
        JAXRSClientFactoryBean factory = new JAXRSClientFactoryBean();
        // 加这句会抛异常
//        factory.getOutInterceptors().add(new MyOutInterceptor(Phase.SEND));
        factory.setServiceClass(HelloEarthService.class);
        factory.setAddress("http://localhost:9999/ws/helloEarth");
        HelloEarthService service = (HelloEarthService)factory.create();
        System.out.println(service.getStudentById(1));
    }

}
