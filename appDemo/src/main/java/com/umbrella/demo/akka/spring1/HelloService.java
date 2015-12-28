package com.umbrella.demo.akka.spring1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by xudazhou on 2015/12/25.
 */
@Component("helloService")
public class HelloService {

    private static final Logger log = LoggerFactory.getLogger(HelloService.class);

    public void say() {
        log.info("HelloService");
    }
}
