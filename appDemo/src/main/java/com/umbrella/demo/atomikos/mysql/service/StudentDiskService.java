package com.umbrella.demo.atomikos.mysql.service;

import com.umbrella.demo.atomikos.mysql.vo.Disk;
import com.umbrella.vo.Student;

/**
 * Created by 大洲 on 15-2-27.
 */
public interface StudentDiskService {
    void saveStudentDisk(Student s, Disk d);
}
