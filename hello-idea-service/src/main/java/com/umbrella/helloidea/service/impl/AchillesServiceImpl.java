package com.umbrella.helloidea.service.impl;

import com.umbrella.helloidea.service.AchillesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by xudazhou on 2016/1/15.
 */
@Component
public class AchillesServiceImpl implements AchillesService {
    private static final Logger log = LoggerFactory.getLogger(AchillesServiceImpl.class);

    public AchillesServiceImpl() {
        log.info("I am AchillesServiceImpl");
    }
}
