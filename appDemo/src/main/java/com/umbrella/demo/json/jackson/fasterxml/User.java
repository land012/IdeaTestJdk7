package com.umbrella.demo.json.jackson.fasterxml;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;
import java.util.Map;

/**
 * create by xudazhou 2017/12/27
 */
public class User {
    private int uId;
    private String userName;
    private Map<String, Double> hobbyPref;
    private BigInteger xxid;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, Double> getHobbyPref() {
        return hobbyPref;
    }

    public void setHobbyPref(Map<String, Double> hobbyPref) {
        this.hobbyPref = hobbyPref;
    }

    public BigInteger getXxid() {
        return xxid;
    }

    public void setXxid(BigInteger xxid) {
        this.xxid = xxid;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
