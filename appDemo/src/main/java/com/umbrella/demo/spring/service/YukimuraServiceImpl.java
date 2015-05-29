package com.umbrella.demo.spring.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by 大洲 on 15-5-28.
 */
@Service("yukimuraService")
public class YukimuraServiceImpl implements YukimuraService {

    private static final Logger log = Logger.getLogger(YukimuraServiceImpl.class);

    private boolean flag = false;

    @Override
    public void hello() {
        if(flag) {
            log.info("YukimuraServiceImpl is running, return");
            return;
        }
        this.flag = true;
        log.info("I am YukimuraServiceImpl.hello() begin");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("I am YukimuraServiceImpl.hello() end");
        this.flag = false;
    }
}
