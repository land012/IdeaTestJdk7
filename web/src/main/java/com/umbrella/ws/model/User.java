package com.umbrella.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by 大洲 on 14-11-22.
 */
@XmlRootElement(name="user")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"userName", "age"})
public class User {
    private String userName;
    private int age;

    public User() { }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "I am " + this.userName + " and I am " + this.age + ".";
    }
}
