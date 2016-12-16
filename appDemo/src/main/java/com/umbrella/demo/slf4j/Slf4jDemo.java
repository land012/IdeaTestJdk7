package com.umbrella.demo.slf4j;

import com.umbrella.vo.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by xudazhou on 2016/11/24.
 */
public class Slf4jDemo {

    private static final Logger log = LoggerFactory.getLogger(Slf4jDemo.class);

    /**
     * 占位符
     */
    @Test
    public void test1() {
        log.info("I am {}, {}", "Jim", "Wang! Wang!");
    }

    /**
     * 打印 Object
     * null 安全
     */
    @Test
    public void test2() {
        User u1 = new User();
        u1.setId(1L);
        u1.setUserName("Mikasa");
        u1.setBirthDay(new Date());
        User u2 = null;
        log.info("I am {}, you are {}", u1, u2);
    }

    /**
     * 打印异常
     */
    @Test
    public void test3() {
        try {
            String str1 = "ab";
            int i1 = Integer.parseInt(str1);
            System.out.println(i1);
        } catch (Exception e) {
            log.info("exception = ", e);
        }
    }

    /**
     * 打印异常
     * 即便使用占位符，仍然打印异常栈
     * 因为使不使用占位符都调用同一个 info(String, Throwable)
     */
    @Test
    public void test4() {
        try {
            String str1 = "ab";
            int i1 = Integer.parseInt(str1);
            System.out.println(i1);
        } catch (Exception e) {
            log.info("exception = {}", e);
        }
    }
}
