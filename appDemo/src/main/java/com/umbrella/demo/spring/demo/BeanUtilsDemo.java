package com.umbrella.demo.spring.demo;

import com.umbrella.demo.apache.commons.vo.Friend;
import com.umbrella.demo.apache.commons.vo.User1;
import com.umbrella.demo.apache.commons.vo.User2;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Created by xudazhou on 2015/8/28.
 */
public class BeanUtilsDemo {
    /**
     * 可以兼容值为 null 的 Date属性
     * 对于名称相同，类型不同的属性，会忽略，不赋值
     */
    @Test
    public void test1() {
        User1 user1 = new User1();
        user1.setId(1L);
        user1.setName("Sanada");
        user1.setBirth(new Date());
        Friend friend1 = new Friend();
        friend1.setId(2L);
        friend1.setName("Orochimaru");
        user1.setFriend(friend1);
        user1.setScore("12");
        user1.setScore1(2L);

        User2 user2 = new User2();

        BeanUtils.copyProperties(user1, user2);

        System.out.println(user2);
    }
}
