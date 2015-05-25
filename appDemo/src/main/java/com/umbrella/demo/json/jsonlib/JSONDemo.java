package com.umbrella.demo.json.jsonlib;

import com.umbrella.vo.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 大洲 on 14-11-24.
 */
public class JSONDemo {
    public void main(String[] args) {
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("tom");
        u1.setBirthDay(new Date());
        JSONObject obj1 = JSONObject.fromObject(u1);
        System.out.println(obj1);
    }

    /**
     * 日期处理
     */
    @Test
    public void test1() {
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("tom");
        u1.setBirthDay(new Date());
        User u2 = new User();
        u2.setId(2);
        u2.setUserName("Snanada Yukimura");
        u2.setBirthDay(new Date());
        List<User> list = new ArrayList<User>();
        list.add(u1);
        list.add(u2);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcesser(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
//        jsonConfig.registerJsonValueProcessor(User.class, Date.class, new DateJsonValueProcesser(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
        System.out.println(jsonArray);
    }

}
