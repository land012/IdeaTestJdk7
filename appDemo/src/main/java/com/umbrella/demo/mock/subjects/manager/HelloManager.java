package com.umbrella.demo.mock.subjects.manager;

import com.umbrella.demo.mock.subjects.dao.HelloDao;
import com.umbrella.vo.User;
import org.springframework.stereotype.Component;

/**
 * Created by 大洲 on 15-1-4.
 */
@Component
public class HelloManager {
    private HelloDao helloDao;

    public void setHelloDao(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

    public User getUser() {
        return helloDao.getUser();
    }

    public User getUse2(long id) {
        return this.helloDao.getUse2(id);
    }
}
