package com.umbrella.demo.spring.demo;

import com.umbrella.demo.apache.commons.vo.Friend;
import com.umbrella.demo.apache.commons.vo.User1;
import com.umbrella.demo.apache.commons.vo.User2;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Created by xudazhou on 2015/8/28.
 * Spring BeanUtils
 */
public class BeanUtilsDemo {
    /**
     * 可以兼容值为 null 的 Date属性
     * 对于名称相同，类型不同的属性，会忽略，不赋值
     * 也会复制父类的成员变量值
     * 如果成员变量是对象，则成员变量指定同一对象
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
        user1.setScore("12"); // 类型不同
        user1.setScore1(2L);  // 类型不同

        User2 user2 = new User2();

        BeanUtils.copyProperties(user1, user2);
        // User1[age=0,birth=Sun May 22 20:14:41 CST 2016,friend=Friend[id=2,name=Orochimaru],homeAddress=<null>,score=12,enrollTime=<null>,score1=2,salary=<null>,id=1,name=Sanada]
        System.out.println(user1);
        System.out.println(user1.getBirth().hashCode());  // -664566638
        System.out.println(user1.getFriend().hashCode()); // 11396073
        // User2[age=0,birth=Sun May 22 20:14:41 CST 2016,friend=Friend[id=2,name=Orochimaru],workAddress=<null>,score=<null>,enrollTime=<null>,score1=<null>,salary=<null>,id=1,name=Sanada]
        System.out.println(user2);
        System.out.println(user2.getBirth().hashCode());  // -664566638
        System.out.println(user2.getFriend().hashCode()); // 11396073
    }

    /**
     * 同一个类的两个对象复制
     */
    @Test
    public void test2() {
        User1 u1 = new User1();
        u1.setId(2L);
        u1.setAge(17);
        u1.setPhone("13803493434");
        User1 u2 = new User1();
        BeanUtils.copyProperties(u1, u2);
        // User1[age=17,phone=13803493434,birth=<null>,friend=<null>,homeAddress=<null>,score=<null>,enrollTime=<null>,score1=<null>,salary=<null>,id=2,name=<null>]
        System.out.println(u1);
        // User1[age=17,phone=13803493434,birth=<null>,friend=<null>,homeAddress=<null>,score=<null>,enrollTime=<null>,score1=<null>,salary=<null>,id=2,name=<null>]
        System.out.println(u2);
    }

    /**
     * java.lang.IllegalArgumentException: Target must not be null
     */
    @Test
    public void test3() {
        User1 u1 = new User1();
        u1.setId(2L);
        u1.setAge(17);
        u1.setPhone("13803493434");
        User1 u2 = null;
        BeanUtils.copyProperties(u1, u2);
        System.out.println(u1);
        System.out.println(u2);
    }
}
