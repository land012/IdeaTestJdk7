package com.umbrella.demo.atomikos.mysql.service;

import com.umbrella.vo.Student;

/**
 * Created by 大洲 on 15-2-26.
 */
public interface StudentService {
    void saveTwo(Student s1, Student s2);
    void saveOneOne(Student s1, Student s2);
}
