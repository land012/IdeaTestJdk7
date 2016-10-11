package com.umbrella.common.service.impl;

import com.umbrella.common.service.StudentDiskService;
import com.umbrella.demo.atomikos.mysql.dao.DiskDao;
import com.umbrella.demo.atomikos.mysql.dao.StudentDao;
import com.umbrella.demo.atomikos.mysql.vo.Disk;
import com.umbrella.vo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 大洲 on 15-2-27.
 */
@Service("studentDiskService")
public class StudentDiskServiceImpl implements StudentDiskService {
//    @Resource
//    private StudentDao studentDao;
    @Resource
    private DiskDao diskDao;

    @Override
    public void saveStudentDisk(Student s, Disk d) {
        diskDao.save(d);
//        if(true) throw new RuntimeException("here is an exception!");
//        studentDao.save(s);
    }
}
