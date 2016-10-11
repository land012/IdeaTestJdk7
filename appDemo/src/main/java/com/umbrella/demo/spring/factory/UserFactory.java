package com.umbrella.demo.spring.factory;

import com.umbrella.vo.User;

/**
 * Created by xudazhou on 2015/11/2.
 */
public class UserFactory {

    public static User create(Long id) {
        User u = new User();
        u.setId(id);
        return u;
    }

    public static User make(Long id, String... strings) {
        User u = new User();
        u.setId(id);
        return u;
    }

}
