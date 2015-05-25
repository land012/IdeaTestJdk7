package com.umbrella.demo.atomikos.mysql.dao;

import com.umbrella.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 大洲 on 15-2-26.
 */
@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {
    private JdbcTemplate jtTest;
//    @Autowired
//    public void setDsTest(DataSource dsTest) {
//        this.jtTest = new JdbcTemplate(dsTest);
//    }

    @Autowired
    public void setJtTest(JdbcTemplate jtTest) {
        this.jtTest = jtTest;
    }

    @Override
    public Student getStudent(long id) {
        return this.jtTest.queryForObject("select * from student t where t.id=" + id, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student s = new Student();
                s.setId(resultSet.getLong("id"));
                s.setName(resultSet.getString("name"));
                return s;
            }
        });
    }

    @Override
    public void save(Student s) {
        this.jtTest.update("insert into student(name) values(?)", s.getName());
    }

    @Override
    public void saveOneOne(Student s1, Student s2) {
        this.jtTest.update("insert into student(name) values(?)", s1.getName());
        if(true) throw new RuntimeException("here is an exception");
        this.jtTest.update("insert into student(name) values(?)", s2.getName());
    }

}
