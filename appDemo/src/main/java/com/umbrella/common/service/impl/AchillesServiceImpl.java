package com.umbrella.common.service.impl;

import com.umbrella.common.service.AchillesService;
import com.umbrella.demo.spring.annotation.SusanooAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by xudazhou on 2015/11/20.
 */
@Service("achillesService")
public class AchillesServiceImpl implements AchillesService {

    private static final Logger log = LoggerFactory.getLogger(AchillesServiceImpl.class);

    @SusanooAnno
    @Override
    public void hello() {
        log.info("I am AchillesService hello");
    }
}
