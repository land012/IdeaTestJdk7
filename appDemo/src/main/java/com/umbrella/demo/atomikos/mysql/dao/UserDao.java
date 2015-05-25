package com.umbrella.demo.atomikos.mysql.dao;

import com.umbrella.demo.atomikos.mysql.vo.User;

/**
 * Created by 大洲 on 15-2-26.
 */
public interface UserDao {
    public User getUser(long id);
}
