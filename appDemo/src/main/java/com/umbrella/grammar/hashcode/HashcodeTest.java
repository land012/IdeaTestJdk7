package com.umbrella.grammar.hashcode;

import com.umbrella.vo.Student;
import org.junit.Test;

/**
 * Created by 大洲 on 15-1-1.
 */
public class HashcodeTest {

    @Test
    public void test1() {
        Student s1 = new Student();
        s1.setId(1);
        Student s2 = new Student();
        s2.setId(2);
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s1.getClass() == s2.getClass()); // true
    }

    @Test
    public void test2() {
        String str1 = "abc";
        String str2 = new String(str1);
        System.out.println(str1.hashCode() == str2.hashCode()); // true
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());

    }


}
