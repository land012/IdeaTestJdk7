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

    /**
     * 注释 @Susanoo 必须加在该方法上，不能加到接口的方法上
     */
    @SusanooAnno
    @Override
    public void hello() {
        log.info("I am AchillesService hello 你好");
    }
}
