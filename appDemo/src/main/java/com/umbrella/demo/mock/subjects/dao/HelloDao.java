package com.umbrella.demo.mock.subjects.dao;

import com.umbrella.vo.User;
import org.springframework.stereotype.Component;

/**
 * Created by 大洲 on 15-1-4.
 */
@Component
public class HelloDao {
    public User getUser() {
        User u = new User();
        u.setId(1);
        u.setUserName("Leah Dizon");
        return u;
    }

    public User getUse2(long id) {
        User u = new User();
        u.setId(id);
        u.setUserName("Leah Dizon");
        return u;
    }
}
