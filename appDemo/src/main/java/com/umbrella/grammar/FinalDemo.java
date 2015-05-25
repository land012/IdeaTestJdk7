package com.umbrella.grammar;

import com.umbrella.vo.User;
import org.junit.Test;

/**
 * Created by 大洲 on 15-1-6.
 */
public class FinalDemo {

    @Test
    public void test1() {
        User u = new User();
        u.setId(3);
        System.out.println(u.getId());
        changeUser(u);
        System.out.println(u.getId());
        changeUser2(u);
        System.out.println(u.getId());
    }

    /**
     * final 表示引用的值不能被改变
     * 但引用指向的对象的属性值是可以被改变的
     * @param u
     */
    private void changeUser(final User u) {
//        u = new User(); // 会报错
        u.setId(1); // 值会被改变
    }

    private void changeUser2(User u) {
        u.setId(2);
    }
}
