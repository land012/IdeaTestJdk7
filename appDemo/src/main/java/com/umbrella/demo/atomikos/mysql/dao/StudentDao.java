package com.umbrella.demo.atomikos.mysql.dao;

import com.umbrella.vo.Student;

/**
 * Created by 大洲 on 15-2-26.
 */
public interface StudentDao {
    Student getStudent(long id);
    void save(Student s);

    void saveOneOne(Student s1, Student s2);
}
