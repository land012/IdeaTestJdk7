package com.umbrella.grammar.extend;

import org.junit.Test;

/**
 * 继承
 * Created by 大洲 on 14-12-24.
 */
public class ExtendDemo {
    /**
     * 将父类对象强制转换为子类对象，异常
     */
    @Test
    public void test1() {
        Person p1 = new Person();
        /*
         * 异常
         * java.lang.ClassCastException: com.umbrella.grammar.extend.Person cannot be cast to com.umbrella.grammar.extend.Student
         */
        Student u1 = (Student)p1;
        System.out.println(u1.getName());
    }

    /**
     * 子类调用父类的方法1，方法1中调用了方法2，子类重写了方法2
     * 那么，实际会调用方法2
     */
    @Test
    public void test2() {
        Student s1 = new Student();
        s1.perform();
    }

    /**
     * 父类和子类的构造函数调用顺序问题
     * 先创建父类，再创建子类
     */
    @Test
    public void test3() {
        Student s1 = new Student();
    }
}
