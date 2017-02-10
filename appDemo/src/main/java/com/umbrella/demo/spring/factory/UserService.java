package com.umbrella.demo.spring.factory;

import com.umbrella.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2017/2/10.
 */
public class UserService {

    private static Logger log = LoggerFactory.getLogger(UserService.class);

    private User user1;
    private User user3;

    public void fn1() {
       log.info(user1.toString());
    }

    public void fn3() {
        log.info(user3.toString());
    }

    /**
     * ====================================================================
     */

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser3() {
        return user3;
    }

    public void setUser3(User user3) {
        this.user3 = user3;
    }
}
