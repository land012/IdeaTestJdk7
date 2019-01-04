package com.umbrella.demo.apache.commons.pool2demo;

import com.umbrella.vo.User;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.util.Random;
import java.util.UUID;

/**
 * create by xudazhou 2018/12/27
 */
public class StringPoolFactory extends BasePooledObjectFactory<User> {
    @Override
    public User create() throws Exception {
        Random rnd = new Random();

        User u = new User();
        u.setId(rnd.nextLong());
        u.setUserName("tom");
        return u;
    }

    @Override
    public PooledObject<User> wrap(User obj) {
        return new DefaultPooledObject<>(obj);
    }
}
