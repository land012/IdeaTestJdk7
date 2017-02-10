package com.umbrella.demo.mock.subjects.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by xudazhou on 2017/1/4.
 */
@Service("bradleyService")
public class BradleyServiceImpl implements BradleyService {

    private static Logger log = LoggerFactory.getLogger(BradleyServiceImpl.class);

    @Override
    public void hello() {
        log.info("I am Leah Dizon");
        log.info(SusanooService.hello("jim"));
    }
}
