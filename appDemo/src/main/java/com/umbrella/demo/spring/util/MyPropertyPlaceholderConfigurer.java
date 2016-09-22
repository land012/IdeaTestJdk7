package com.umbrella.demo.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by xudazhou on 2015/10/14.
 * 从 Spring 中 .properties 中的配置
 */
public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static Map<String, String> map;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        map = new HashMap<>();
        for(Object key : props.keySet()) {
            map.put(key.toString(), props.getProperty(key.toString()));
        }
    }

    public static String getProperty(String key) {
        return map.get(key);
    }
}
