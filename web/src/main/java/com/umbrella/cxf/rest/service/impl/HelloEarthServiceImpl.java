package com.umbrella.cxf.rest.service.impl;

import com.umbrella.cxf.rest.service.HelloEarthService;
import com.umbrella.ws.model.Student;
import com.umbrella.ws.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.FormParam;
import java.io.InputStream;

/**
 * Created by 大洲 on 14-11-23.
 */
public class HelloEarthServiceImpl implements HelloEarthService {

    private static final Logger log = LoggerFactory.getLogger(HelloEarthServiceImpl.class);

    @Override
    public User getUserByUserName(String userName) {
        log.info("User name = " + userName);
        User u = new User();
        u.setUserName(userName);
        u.setAge(19);
        return u;
    }

    @Override
    public Student getStudentById(int id) {
        log.info("studentId=" + id);
        Student student = new Student();
        student.setId(id);
        student.setStuName("Tom");
        student.setGender("male");
        return student;
    }

    @Override
    public Student getStudentByName(String stuName) {
        log.info("stuName=" + stuName);
        Student student = new Student();
        student.setId(3);
        student.setStuName(stuName);
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

    @Override
    public String readInputStream(InputStream is) {
        StringBuilder params = new StringBuilder();
        try {
            byte[] buf = new byte[1024];
            int i=-1;
            while ((i=is.read(buf))!=-1) {
                params.append(new String(buf, 0, i, "utf-8"));
            }
            log.info("params=" + params);
        } catch (Exception e) {
            log.error("readInputStream exception,", e);
        }

        return "Hello InputStream";
    }
}
