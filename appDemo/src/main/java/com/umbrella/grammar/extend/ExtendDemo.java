package com.umbrella.grammar.extend;

/**
 * 继承
 * Created by 大洲 on 14-12-24.
 */
public class ExtendDemo {
    public static void main(String[] args) {
        Person p1 = new Person();
        /*
         * 异常
         * java.lang.ClassCastException: com.umbrella.grammar.extend.Person cannot be cast to com.umbrella.grammar.extend.Student
         */
        Student u1 = (Student)p1;
        System.out.println(u1.getName());
    }
}
