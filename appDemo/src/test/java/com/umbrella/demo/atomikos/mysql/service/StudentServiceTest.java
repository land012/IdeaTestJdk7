package com.umbrella.demo.atomikos.mysql.service;

import com.umbrella.vo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by 大洲 on 15-2-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config.xml" })
public class StudentServiceTest {
    @Resource
    private StudentService studentService;

    /**
     * 可以回滚，两条记录都没有插入
     */
    @Test
    public void testSaveTwo1() {
        Student s1 = new Student();
        s1.setName("Michael");
        Student s2 = new Student();
        s2.setName("Hatake Kakashi");
        studentService.saveTwo(s1, s2);
    }

    /**
     * 可以回滚
     */
    @Test
    public void testSaveOneOne1() {
        Student s1 = new Student();
        s1.setName("Michael");
        Student s2 = new Student();
        s2.setName("Hatake Kakashi");
        studentService.saveOneOne(s1, s2);
    }

}
