package com.umbrella.vo;

import java.io.Serializable;

/**
 * Created by 大洲 on 14-12-16.
 */
public class Student implements Serializable {
    private long id;
    private String name;
    private String school;
    private int classId; // 班级

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + school.hashCode();
        result = 31 * result + classId;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (classId != student.classId) return false;
        if (id != student.id) return false;
        if (!name.equals(student.name)) return false;
        if (!school.equals(student.school)) return false;

        return true;
    }
}
