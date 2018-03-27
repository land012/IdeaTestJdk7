package com.umbrella.demo.json.fastjson;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * create by xudazhou 2017/12/27
 */
public class User {

    @JSONField(name = "user_id")
    private int uId;

    @JSONField(name = "user_nameee")
    private String userName;

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
}
