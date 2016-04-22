package com.umbrella.demo.dozer;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2016/4/17.
 */
public class DozerDemo {

    private static final Logger log = LoggerFactory.getLogger(DozerDemo.class);

    @Test
    public void test1() {
        UserA userA = new UserA();
        userA.setId(1);
        userA.setName("Sanada");
        userA.setAge(22);

        Mapper mapper = new DozerBeanMapper();
        UserB userB = mapper.map(userA, UserB.class);
        log.info(userB + "");
    }
}
