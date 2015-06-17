package com.umbrella.demo.json.jackson2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umbrella.vo.User;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 大洲 on 14-12-2.
 * Jackson JSON解析
 */
public class Jackson2Demo {

    private static Logger log = Logger.getLogger(Jackson2Demo.class);

    /**
     * Object - JSON
     * JSON - Object
     */
    @Test
    public void test0() {
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("Alphonse Elric");
        u1.setBirthDay(new Date());
        u1.setGender(0);
        u1.setUrgencyContactName("Leah Dizon");
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); // Date类型转化为 JSON 时的样式
        try {
            // 生成JSON串
            String str1 = om.writeValueAsString(u1);
            // {"id":1,"userName":"Alphonse Elric","birthDay":"2015-04-21 17:59:36","gender":0,"urgencyContactName":"Leah Dizon","age":null}
            System.out.println(str1);
            // 从JSON串生成对象
            User u2 = om.readValue(str1, User.class);
            System.out.println(u2.getUserName());
            System.out.println(u2.getBirthDay());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对象生成 JSON
     */
    @Test
    public void test1()  {
        try {
            User u1 = new User();
            u1.setId(1);
            u1.setUserName("Alphonse Elric");
            u1.setBirthDay(new Date());
            u1.setGender(0);
            u1.setUrgencyContactName("Leah Dizon");
            ObjectMapper om = new ObjectMapper();
            om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); // Date类型转化为 JSON 时的样式
            String str1 = om.writeValueAsString(u1); // 生成JSON串
            System.out.println(str1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * List 生成 JSON
     */
    @Test
    public void testGenString2()  {
        try {
            User u1 = new User();
            u1.setId(1);
            u1.setUserName("Alphonse Elric");
            u1.setBirthDay(new Date());
            u1.setGender(0);
            u1.setUrgencyContactName("Edward");

            User u2 = new User();
            u2.setId(2);
            u2.setUserName("Leah Dizon");
            u2.setBirthDay(new Date());
            u2.setGender(1);
            u2.setUrgencyContactName("Oda Nobunaga");

            List<User> list = new ArrayList<User>();
            list.add(u1);
            list.add(u2);

            ObjectMapper om = new ObjectMapper();
            om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); // Date类型转化为 JSON 时的样式
            String str1 = om.writeValueAsString(list); // 生成JSON串
            System.out.println(str1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析 JSON 对 Object
     */
    @Test
    public void test2() {
        try {
            String str1 = "{\"id\":1,\"userName\":\"Alphonse Elric\",\"birthDay\":\"2015-01-07 11:10:52\",\"gender\":0,\"address\":\"Bei Jing\"}";
            ObjectMapper om = new ObjectMapper();
            om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 忽略字符串中不识别的属性
            User u1 = om.readValue(str1, User.class);
            System.out.println(u1.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析 json 为 List
     */
    @Test
    public void test3() {
        try {
            String json1 = "[{\"id\":1,\"userName\":\"Alphonse Elric\",\"birthDay\":\"2015-03-11 11:37:16\",\"gender\":0,\"urgencyContactName\":\"Edward\"},{\"id\":2,\"userName\":\"Leah Dizon\",\"birthDay\":\"2015-03-11 11:37:16\",\"gender\":1,\"urgencyContactName\":\"Oda Nobunaga\"}]";
            ObjectMapper om = new ObjectMapper();
            om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            List<User> list1 = om.readValue(json1, List.class);
            log.info("list1.size()=" + list1.size());
            log.info(list1);
            for(User u : list1) {
                log.info(u);
                log.info(u.getUserName());
            }
        } catch (Exception e) {

        }
    }

    /**
     * 复杂 json 解析
     * 获取 data Node
     */
    public void testFromJson4() {
        String json = "\"status\":\"true\", \"data\":{\"sno\":1,\"name\":\"Uchiha Sasuke\",\"birth\":\"2015-03-11 14:29:22\",\"school\":{\"id\":1,\"name\":\"Harvard\"}}";
    }
}
