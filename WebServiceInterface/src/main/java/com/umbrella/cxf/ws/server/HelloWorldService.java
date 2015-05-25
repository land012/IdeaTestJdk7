package com.umbrella.cxf.ws.server;

//import com.umbrella.ws.model.User;
//import com.umbrella.ws.model.Users;

import javax.jws.WebService;

/**
 * Created by 大洲 on 14-11-20.
 */
@WebService
public interface HelloWorldService {
    String sayHello(String name);
//    User getUser(String name);
//    Users getUserList();
}
