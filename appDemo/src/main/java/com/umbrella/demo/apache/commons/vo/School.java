package com.umbrella.demo.apache.commons.vo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xudazhou on 2015/8/27.
 */
public class School {
    private Long id;
    private String name;
    private String address;

    private List<User1> user1List;
    private User2 user2;

    private BigDecimal schooling;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<User1> getUser1List() {
        return user1List;
    }

    public void setUser1List(List<User1> user1List) {
        this.user1List = user1List;
    }

    public User2 getUser2() {
        return user2;
    }

    public void setUser2(User2 user2) {
        this.user2 = user2;
    }

    public BigDecimal getSchooling() {
        return schooling;
    }

    public void setSchooling(BigDecimal schooling) {
        this.schooling = schooling;
    }
}
