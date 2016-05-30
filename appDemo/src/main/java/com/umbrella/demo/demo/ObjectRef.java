package com.umbrella.demo.demo;

import com.umbrella.vo.ClassGrade;
import com.umbrella.vo.Student;
import com.umbrella.vo.User;
import org.junit.Test;

/**
 * Created by 大洲 on 14-11-17.
 * 对象引用
 */
public class ObjectRef {
    @Test
    public void test1() {
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("tom");
        User u2 = u1;
        u1.setUserName("jim");
        System.out.println(u2.getUserName()); // jim
    }

    /**
     * 对象引用
     */
    @Test
    public void test2() {
        Student s1 = new Student();
        ClassGrade cg1 = s1.getClassGrade();
        ClassGrade cg = new ClassGrade();
        s1.setClassGrade(cg);
        System.out.println(cg1==null); // true
        System.out.println(s1.getClassGrade()==null); // false
    }

    /**
     * 对象引用
     */
    @Test
    public void test2_2() {
        Student s1 = new Student();
        ClassGrade cg1 = s1.getClassGrade();
        cg1 = new ClassGrade();
        System.out.println(cg1==null); // false
        System.out.println(s1.getClassGrade()==null); // true
    }
}
