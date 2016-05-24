package com.umbrella.demo.java.lang;

import com.umbrella.vo.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2016/5/24.
 */
public class CloneDemo {

    private static final Logger log = LoggerFactory.getLogger(CloneDemo.class);

    /**
     * 直接调用 Object clone
     * java.lang.CloneNotSupportedException: com.umbrella.vo.User
     */
    @Test
    public void test1() {
        try {
            User u1 = new User(1, "Mikasa");
            User u2 = u1.clone();
            log.info(u1.hashCode() + "");
            log.info(u2.hashCode() + "");
        } catch (Exception e) {
            log.error("异常了", e);
        }
    }
}
