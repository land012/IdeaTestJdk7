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

    /**
     * 比较
     */
    @Test
    public void test2() {
        System.out.println(Integer.compare(1, 2)); // -1
    }

    @Test
    public void test3() {
        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void test4() {
        System.out.println(Integer.toHexString(Integer.MAX_VALUE)); // 7fffffff
        int hashCode = "abcdsed".hashCode();
        System.out.println(Integer.toHexString(hashCode & 0xff)); // d0
        System.out.println(Integer.toHexString(10 & 0xff)); // a
    }
}
