package com.umbrella.cxf.rest.service.impl;

import com.umbrella.cxf.rest.service.HelloEarthService;
import com.umbrella.ws.model.Student;
import com.umbrella.ws.model.User;

/**
 * Created by 大洲 on 14-11-23.
 */
public class HelloEarthServiceImpl implements HelloEarthService {
    @Override
    public User getUserByUserName(String userName) {
        User u = new User();
        u.setUserName(userName);
        u.setAge(19);
        return u;
    }

    @Override
    public Student getStudentById(int id) {
        Student student = new Student();
        student.setId(id);
        student.setStuName("Tom");
        student.setGender("male");
        return student;
    }

    @Override
    public Student getStudentByStuName(String stuName) {
        Student s = new Student();
        s.setId(2);
        s.setStuName(stuName);
        s.setGender("female");
        return s;
    }
}
