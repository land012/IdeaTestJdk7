package com.umbrella.grammar.proxy.service;

import com.umbrella.grammar.proxy.service.UserService;
import com.umbrella.vo.User;

/**
 * Created by 大洲 on 15-1-7.
 */
public class UserServiceImpl implements UserService {
    public void save(User user) {
        System.out.println("saved user = " + user.getUserName());
    }
}
