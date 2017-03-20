package com.umbrella.protobuf.demo2;

/**
 * Created by xudazhou on 2017/3/7.
 */
public class Student {
    private int sid;
    private String sname;
    private Teacher teacher;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
