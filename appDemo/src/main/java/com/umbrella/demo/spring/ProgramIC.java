package com.umbrella.demo.spring;

import com.umbrella.demo.spring.service.LeahService;
import com.umbrella.demo.spring.service.MikasaService;
import com.umbrella.demo.spring.util.SpringContextHelper;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 大洲 on 15-4-18.
 * 编程注入对象
 * 从 Spring容器 中手动获取对象
 */
public class ProgramIC {

    private LeahService leahService;
    private SpringContextHelper springContextHelper;

    public void setLeahService(LeahService leahService) {
        this.leahService = leahService;
    }

    public void setSpringContextHelper(SpringContextHelper springContextHelper) {
        this.springContextHelper = springContextHelper;
    }

    public ProgramIC() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        // 注入 bean
        context.getAutowireCapableBeanFactory().autowireBeanProperties(this, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
    }

    public static void main(String[] args) {
        ProgramIC programIC = new ProgramIC();
        // 注入
        programIC.leahService.hello("Leah Dizon");

        // 直接从容器中获取
        MikasaService mikasaService = (MikasaService)programIC.springContextHelper.getBean("mikasaService");
        mikasaService.hello("Nobunaga");
    }
}
