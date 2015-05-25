package com.umbrella.demo.demo;

import com.umbrella.vo.User;

/**
 * Created by 大洲 on 14-11-17.
 * 对象引用
 */
public class ObjectRef {
    public static void main(String[] args) {
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("tom");
        User u2 = u1;
        u1.setUserName("jim");
        System.out.println(u2.getUserName()); // jim
    }
}
