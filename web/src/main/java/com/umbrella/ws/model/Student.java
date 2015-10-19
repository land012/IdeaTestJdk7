package com.umbrella.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by 大洲 on 14-11-23.
 */
//@XmlRootElement(name="student")
public class Student {
    private int id;
    private String stuName;
    private String gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.id + " " + this.stuName + " " + this.gender;
    }
}
