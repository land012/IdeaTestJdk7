package com.umbrella.demo.atomikos.mysql.service;

import com.umbrella.demo.atomikos.mysql.vo.Disk;
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
public class StudentDiskServiceTest {
    @Resource
    private StudentDiskService studentDiskService;

    /**
     * 没有事务，没有异常，保存成功
     */
    @Test
    public void testSaveStudentDisk1() {
        Disk d = new Disk();
        d.setName("光盘5");
        d.setCid(2);
        d.setPid(1);
        d.setRemark("这里是备注5");
        Student s = new Student();
        s.setName("Hatake Kakashi");
        this.studentDiskService.saveStudentDisk(s, d);
    }

    /**
     * 没有事务，有异常，保存一半
     */
    @Test
    public void testSaveStudentDisk2() {
        Disk d = new Disk();
        d.setName("光盘5");
        d.setCid(2);
        d.setPid(1);
        d.setRemark("这里是备注5");
        Student s = new Student();
        s.setName("Hatake Kakashi");
        this.studentDiskService.saveStudentDisk(s, d);
    }

    /**
     * 使用 Atomikos 管理分布式事务
     * 有异常，可以回滚
     */
    @Test
    public void testSaveStudentDisk3() {
        Disk d = new Disk();
        d.setName("光盘5");
        d.setCid(2);
        d.setPid(1);
        d.setRemark("这里是备注5");
        Student s = new Student();
        s.setName("Hatake Kakashi");
        this.studentDiskService.saveStudentDisk(s, d);
    }

    /**
     * 使用 Atomikos 管理分布式事务
     * 没有异常，执行成功
     */
    @Test
    public void testSaveStudentDisk4() {
        Disk d = new Disk();
        d.setName("光盘5");
        d.setCid(2);
        d.setPid(1);
        d.setRemark("这里是备注5");
        Student s = new Student();
        s.setName("Hatake Kakashi");
        this.studentDiskService.saveStudentDisk(s, d);
    }
}
