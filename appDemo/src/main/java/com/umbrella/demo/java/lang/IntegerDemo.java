package com.umbrella.demo.java.lang;

import com.umbrella.vo.User;
import org.junit.Test;

/**
 * Created by 大洲 on 15-3-12.
 */
public class IntegerDemo {
    /**
     * 对于 int 类型，不指定值时，默认为 0
     * 对于 Integer 类型，不指定值时，默认为 null
     */
    @Test
    public void test1() {
        User u1 = new User();
        System.out.println("id=" + u1.getId()); // 0
        System.out.println("age=" + u1.getAge()); // null
    }
}
