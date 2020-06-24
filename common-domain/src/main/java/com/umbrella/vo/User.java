package com.umbrella.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 大洲 on 14-11-13.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -2982309772740029451L;
    private long id;
    private String userName;
    public Date birthDay;
    private int gender; // 0 - male, 1 - female
    private String urgencyContactName;
    private Integer age;
    private Date enrolDate;

    public User() {
    }

    public User(long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 注解的作用 是用来定制 springmvc 响应的json格式
     * @return
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getUrgencyContactName() {
        return urgencyContactName;
    }

    public void setUrgencyContactName(String urgencyContactName) {
        this.urgencyContactName = urgencyContactName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public Date getEnrolDate() {
        return enrolDate;
    }

    public void setEnrolDate(Date enrolDate) {
        this.enrolDate = enrolDate;
    }

    @Override
    public int hashCode() {
        return (id+userName).hashCode();
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User)super.clone();
    }
}
