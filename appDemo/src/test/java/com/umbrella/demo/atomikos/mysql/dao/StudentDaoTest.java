package com.umbrella.demo.atomikos.mysql.dao;

import com.umbrella.vo.Student;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 大洲 on 15-2-26.
 */
public class StudentDaoTest {
    private ApplicationContext context;
    private StudentDao studentDao;

    @Before
    public void before() {
        this.context = new ClassPathXmlApplicationContext("spring-config.xml");
        this.studentDao = this.context.getBean("studentDao", StudentDao.class);
    }

    @Test
    public void testGetStudent() {
        Student s = studentDao.getStudent(1);
        System.out.println(s.getName());
    }

    @Test
    public void testSave1() {
        Student s = new Student();
        s.setName("Mikasa");
        this.studentDao.save(s);
    }

    /**
     * 在没有声明式事务的情况下
     * 没有事务，第一条记录保存成功，第二条记录保存失败
     */
    @Test
    public void testSave2() {
        Student s1 = new Student();
        s1.setName("Michael");
        this.studentDao.save(s1);
        if(true) throw new RuntimeException("here is an exception!");
        Student s2 = new Student();
        s2.setName("Hatake Kakashi");
        this.studentDao.save(s2);
    }

    @Test
    public void testSaveOneOne1() {
        Student s1 = new Student();
        s1.setName("Michael");
        Student s2 = new Student();
        s2.setName("Hatake Kakashi");
        this.studentDao.saveOneOne(s1, s2);
    }
}
