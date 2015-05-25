package com.umbrella.demo.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by 大洲 on 15-4-18.
 * 获取 ApplicationContext
 */
@Component
public class SpringContextHelper implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
