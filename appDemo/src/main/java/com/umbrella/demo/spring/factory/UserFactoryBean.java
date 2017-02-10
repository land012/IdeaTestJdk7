package com.umbrella.demo.spring.factory;

import com.umbrella.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by xudazhou on 2015/11/14.
 */
public class UserFactoryBean implements FactoryBean<User> {

    private static Logger log = LoggerFactory.getLogger(UserFactoryBean.class);

    private User user;

    @Override
    public User getObject() throws Exception {
        log.info("hello factory bean");
        this.user = new User();
        this.user.setId(1L);
        this.user.setUserName("Susanoo");
        return this.user;
    }

    @Override
    public Class<? extends User> getObjectType() {
        return this.user==null?User.class:this.user.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
