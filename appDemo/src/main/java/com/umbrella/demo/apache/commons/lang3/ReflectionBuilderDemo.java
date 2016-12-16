package com.umbrella.demo.apache.commons.lang3;

import com.umbrella.demo.apache.commons.vo.School;
import com.umbrella.demo.apache.commons.vo.Student;
import com.umbrella.demo.apache.commons.vo.User1;
import com.umbrella.demo.apache.commons.vo.User2;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xudazhou on 2016/4/27.
 */
public class ReflectionBuilderDemo {
    /**
     * 打印类的属性值
     */
    @Test
    public void test1() {
        School school = new School();
        school.setId(10000L);
        school.setName("黑魔法学院");
        // com.umbrella.demo.apache.commons.vo.School@1f35e0a[id=10000,name=黑魔法学院,address=<null>]
        System.out.println(ReflectionToStringBuilder.toString(school));
        /**
         * com.umbrella.demo.apache.commons.vo.School@f4bf02[
         id=10000
         name=黑魔法学院
         address=<null>
         ]
         */
        System.out.println(ReflectionToStringBuilder.toString(school, ToStringStyle.MULTI_LINE_STYLE));

        // School[id=10000,name=黑魔法学院,address=<null>]
        System.out.println(ReflectionToStringBuilder.toString(school, ToStringStyle.SHORT_PREFIX_STYLE));
        // 10000,黑魔法学院,<null>
        System.out.println(ReflectionToStringBuilder.toString(school, ToStringStyle.SIMPLE_STYLE));
    }

    /**
     * 只打印当前类的成员（调用成员的 toString()），如果成员为类，不打印成员的成员
     */
    @Test
    public void test2() {
        School school = new School();
        school.setId(10000L);
        school.setName("黑魔法学院");
        User1 user1 = new User1();
        user1.setId(20001L);
        user1.setName("Sanada");
        User2 user2 = new User2();
        user2.setId(20000L);
        user2.setName("Nobunaga");
        List<User1> user1List = new ArrayList<>();
        user1List.add(user1);
        school.setUser1List(user1List);
        school.setUser2(user2);
        school.setSchooling(new BigDecimal(99.9));
        System.out.println(ReflectionToStringBuilder.toString(school, ToStringStyle.MULTI_LINE_STYLE));
    }

    /**
     * 不会抛异常
     */
    @Test
    public void test3() {
        // <null>
        System.out.println(ReflectionToStringBuilder.toString(null));
    }

    @Test
    public void test4() {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("Alphonse");
        student1.setGrade(2);
        System.out.println(ReflectionToStringBuilder.toString(student1, ToStringStyle.MULTI_LINE_STYLE));
    }
}
