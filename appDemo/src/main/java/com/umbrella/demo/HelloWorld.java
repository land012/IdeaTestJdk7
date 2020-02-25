package com.umbrella.demo;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2016/12/16.
 */
public class HelloWorld {

    private static Logger log = LoggerFactory.getLogger(HelloWorld.class);

    @Test
    public void test1() {
        System.out.println(6);
        System.out.println("Hello");
    }

    @Test
    public void hello() {
        try {
            int i = 0;
            int j = 15 / i;
        } catch (Exception e) {
            log.info("xxxx {}", "yyy", "zzz"); // xxxx yyy
            log.info("xxx, {}, yy", "zz", e); // 会打印堆栈

        }
    }

//    int hello(String tom) {
//        return -1;
//    }
}
