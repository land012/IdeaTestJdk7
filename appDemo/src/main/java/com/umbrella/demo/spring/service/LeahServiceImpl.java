package com.umbrella.demo.spring.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


/**
 * Created by 大洲 on 15-4-18.
 */
@Service("leahService")
public class LeahServiceImpl implements LeahService {

    private static final Logger log = Logger.getLogger(LeahServiceImpl.class);

    public LeahServiceImpl() {
        log.info("I am LeahServiceImpl");
    }

    public void hello(String name) {
        log.info("Hello " + name);
    }
}
