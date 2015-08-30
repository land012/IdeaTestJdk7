package com.umbrella.demo.apache.commons.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by xudazhou on 2015/8/27.
 */
public class User1 {
    private Long id;
    private String name;
    private int age;
    private Date birth;
    private Friend friend;
    private String homeAddress; // 这个属性 User2 中没有
    private String score;
    private Date enrollTime;
    private Long score1;
    private BigDecimal salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(Date enrollTime) {
        this.enrollTime = enrollTime;
    }

    public Long getScore1() {
        return score1;
    }

    public void setScore1(Long score1) {
        this.score1 = score1;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                ", friend=" + friend +
                ", homeAddress='" + homeAddress + '\'' +
                ", score='" + score + '\'' +
                ", enrollTime=" + enrollTime +
                ", score1=" + score1 +
                ", salary=" + salary +
                '}';
    }
}
