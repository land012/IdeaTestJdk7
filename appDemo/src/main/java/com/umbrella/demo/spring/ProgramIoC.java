package com.umbrella.demo.spring;

import com.umbrella.demo.spring.service.LeahService;
import com.umbrella.demo.spring.service.MikasaService;
import com.umbrella.demo.spring.util.MyPropertyPlaceholderConfigurer;
import com.umbrella.demo.spring.util.SpringContextHelper;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 大洲 on 15-4-18.
 * 编程注入对象
 * 从 Spring容器 中手动获取对象
 */
public class ProgramIoC {

    private LeahService leahService;
    private SpringContextHelper springContextHelper;

    public void setLeahService(LeahService leahService) {
        this.leahService = leahService;
    }

    public void setSpringContextHelper(SpringContextHelper springContextHelper) {
        this.springContextHelper = springContextHelper;
    }

    public ProgramIoC() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        // 注入 bean
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    public static void main(String[] args) {
        ProgramIoC programIoC = new ProgramIoC();
        // 注入
        programIoC.leahService.hello("Leah Dizon");

        // 直接从容器中获取
        MikasaService mikasaService = (MikasaService)programIoC.springContextHelper.getBean("mikasaService");
        mikasaService.hello("Nobunaga");

        /**
         * 获取 Spring 配置文件内容
         */
        System.out.println(MyPropertyPlaceholderConfigurer.getProperty("k1"));
    }
}
