package com.umbrella.demo.atomikos.mysql.service;

import com.umbrella.demo.atomikos.mysql.dao.StudentDao;
import com.umbrella.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 大洲 on 15-2-26.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    /**
     * @param s1
     * @param s2
     */
    @Override
    public void saveTwo(Student s1, Student s2) {
        studentDao.save(s1);
        if(true) throw new RuntimeException("here is an excpetion");
        studentDao.save(s2);
    }

    @Override
    public void saveOneOne(Student s1, Student s2) {
        studentDao.saveOneOne(s1, s2);
    }
}
