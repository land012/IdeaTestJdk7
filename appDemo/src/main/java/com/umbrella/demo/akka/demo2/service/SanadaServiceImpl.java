package com.umbrella.demo.akka.demo2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2015/11/2.
 */
public class SanadaServiceImpl implements SanadaService {

    private Logger log = LoggerFactory.getLogger(SanadaServiceImpl.class);

    @Override
    public void saySanada() {
        log.info("this is Sanada");
    }
}
