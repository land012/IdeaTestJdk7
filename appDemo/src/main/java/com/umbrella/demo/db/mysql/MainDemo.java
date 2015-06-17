package com.umbrella.demo.db.mysql;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Created by 大洲 on 15-6-15.
 * MySql事务管理
 * 乐观锁处理并发
 */
public class MainDemo {

    private static final Logger log = Logger.getLogger(MainDemo.class);

    private AccountService accountService;

    public static void main(String[] args) {
        final MainDemo mainDemo = new MainDemo();
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "spring-config2.xml" });
        context.getAutowireCapableBeanFactory().autowireBeanProperties(mainDemo, DefaultListableBeanFactory.AUTOWIRE_BY_NAME, false);
        log.info("main begin");

        // 查询测试
//        Map m1 = mainDemo.accountService.query("SELECT * FROM account t where t.id=1");
//        if(m1!=null) {
//            System.out.println(m1);
//        } else {
//            System.out.println("m1 is null");
//        }

        // 插入
//        String sql = "INSERT INTO opetlog(msg, addtime) values('haha', NOW())";
//        System.out.println(mainDemo.accountService.insert(sql));

        // 插入，事务
//        String sql1 = "INSERT INTO opetlog(msg, addtime) values('haha1', NOW())";
//        String sql2 = "INSERT INTO opetlog(msg, addtime) values('haha2', NOW())";
//        mainDemo.accountService.insertTow(sql1, sql2);

//        mainDemo.accountService.updateAccount("");

        // 乐观锁，修改同一条记录防并发
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                log.info(mainDemo.accountService.updateAccount(1, "INSERT INTO opetlog(msg, addtime) values('heihie1', NOW())"));
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                log.info(mainDemo.accountService.updateAccount(1, "INSERT INTO opetlog(msg, addtime) values('heihie2', NOW())"));
//            }
//        }).start();

        /**
         * 乐观锁，修改不同记录，不会影响另一条记录
         * 说明只锁了记录，没锁表
          */
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                log.info(mainDemo.accountService.updateAccount(1, "INSERT INTO opetlog(msg, addtime) values('heihie1', NOW())"));
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                log.info(mainDemo.accountService.updateAccount(2, "INSERT INTO opetlog(msg, addtime) values('heihie2', NOW())"));
//            }
//        }).start();

        /**
         * 一个线程的 update ，并不会阻塞另一个线程的 select
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                log.info(mainDemo.accountService.update("update account t set t.balance=1020 where t.id=1"));
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info(mainDemo.accountService.query("select * from account t where t.id=1"));
            }
        }).start();
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
