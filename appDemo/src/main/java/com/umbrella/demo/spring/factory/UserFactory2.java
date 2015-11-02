package com.umbrella.demo.spring.factory;

import com.umbrella.vo.User;

/**
 * Created by xudazhou on 2015/11/2.
 */
public class UserFactory2 {

    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public User create() {
        User u = new User();
        u.setId(this.id);
        return u;
    }
}
