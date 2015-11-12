package com.umbrella.demo.spring.util;

import java.util.Properties;

/**
 * 读取配置文件
 * Created by xudazhou on 2015/11/9.
 */
public class MyPropertyFactoryBean {
    private static Properties commonProperties;

    public void setCommonProperties(Properties commonProperties) {
        this.commonProperties = commonProperties;
    }

    public static String getProperty(String key) {
        return (String)commonProperties.get(key);
    }
}
