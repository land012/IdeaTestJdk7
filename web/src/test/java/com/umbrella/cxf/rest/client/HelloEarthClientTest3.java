package com.umbrella.cxf.rest.client;

import com.umbrella.ws.model.User;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

/**
 * Created by 大洲 on 14-11-23.
 * REST 客户端
 * 使用 http 的方式调用 REST 服务端
 */
public class HelloEarthClientTest3 {

    @Test
    public void  test1() {
        WebClient client = WebClient.create("http://localhost:8080/rs");
        User u = client.path("helloEarth/user/tom").accept(MediaType.APPLICATION_XML).get(User.class);
        System.out.println(u);
    }
}
