package com.umbrella.demo.spring.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by 大洲 on 15-4-18.
 */
@Service("mikasaService")
public class MikasaServiceImpl implements MikasaService {

    private static final Logger log = Logger.getLogger(MikasaServiceImpl.class);

    @Override
    public void hello(String name) {
        log.info("Mikasa:" + name);
    }
}
