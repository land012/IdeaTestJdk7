package com.umbrella.demo.atomikos.mysql.dao;

import com.umbrella.demo.atomikos.mysql.vo.User;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by 大洲 on 15-2-26.
 */
public class UserDaoTest {
    private static Logger log = Logger.getLogger(UserDaoTest.class);

    @Test
    public void testGetUser() {
        UserDao userDao = new UserDaoImpl();
        User u1 = userDao.getUser(1);
        log.info("u1.getUsername()=" + u1.getUsername());
    }
}
