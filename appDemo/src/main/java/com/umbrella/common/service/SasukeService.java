package com.umbrella.common.service;

import com.umbrella.demo.spring.annotation.SusanooAnno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by xudazhou on 2015/11/20.
 */
@Service
public class SasukeService {

    private static final Logger log = LoggerFactory.getLogger(SasukeService.class);

    @SusanooAnno
    public void hello() {
        log.info("I am SasukeService hello");
    }
}
