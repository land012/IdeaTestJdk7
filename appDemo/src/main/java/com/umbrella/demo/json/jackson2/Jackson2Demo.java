package com.umbrella.demo.json.jackson2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.umbrella.vo.User;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
     * jackson 2.4.3
     * 日期的默认格式为 yyyy-MM-dd HH:mm:ss
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
//            om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); // Date类型转化为 JSON 时的样式
            String str1 = om.writeValueAsString(u1); // 生成JSON串
            System.out.println(str1); // {"id":1,"userName":"Alphonse Elric","birthDay":"2017-01-23 16:58:50","gender":0,"urgencyContactName":"Leah Dizon","age":null,"enrolDate":null}
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
     * 解析 JSON 到 Object
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
    @Test
    public void testFromJson4() throws Exception {
        String json = "{ \"status\":\"true\", \"data\":{\"sno\":1,\"name\":\"Uchiha Sasuke\",\"birth\":\"2015-03-11 14:29:22\",\"school\":{\"id\":1,\"name\":\"Harvard\"}}, \"num1\":1, \"num2\":1.1, \"str1\":\"\", \"str2\":null,\"birth\":\"2015-03-11 14:29:22\" }";
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> map1 = om.readValue(json, Map.class);
        System.out.println(map1.get("status").getClass()); // class java.lang.String
        System.out.println(map1.get("data").getClass()); // class java.util.LinkedHashMap
        System.out.println(map1.get("num1").getClass()); // class java.lang.Integer
        System.out.println(map1.get("num2").getClass()); // class java.lang.Double
        System.out.println(map1.get("str1").getClass());
        // java.lang.NullPointerException
//        System.out.println(map1.get("str2").getClass());
        System.out.println(map1.get("birth").getClass()); // class java.lang.String
        // java.lang.NullPointerException
//        System.out.println(map1.get("xxx").getClass());
    }

    /**
     * UNWRAP_ROOT_VALUE
     * 最外层的 Map属性作为 root，会被忽略掉
     * 但是属性名必须是 Map
     */
    @Test
    public void testFromJson5() throws Exception {
        String json = "{\"Map\":{\"status\":\"true\", \"data\":{\"sno\":1,\"name\":\"Uchiha Sasuke\",\"birth\":\"2015-03-11 14:29:22\",\"school\":{\"id\":1,\"name\":\"Harvard\"}}}}";
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        Map<String, Object> map1 = om.readValue(json, Map.class);
        System.out.println(map1.get("status"));
        System.out.println(map1.get("status").getClass()); // class java.lang.String
        System.out.println(map1.get("data").getClass()); // class java.util.LinkedHashMap
    }

    /**
     * Object 是 null
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        ObjectMapper om = new ObjectMapper();
        String str1 = om.writeValueAsString(null);
        if (str1 == null) {
            System.out.println("str1 == null");
        } else {
            System.out.println("str1 is null"); // str1 is null
        }
    }

    /**
     * BOM开关的字符串
     */
    @Test
    public void test6() throws Exception {
        String str1 = "\uFEFF{\"sno\":1,\"name\":\"Uchiha Sasuke\",\"birth\":\"2015-03-11 14:29:22\"}";
        if (str1!=null && str1.startsWith("\uFEFF")) {
            str1 = str1.substring(1);
        }
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> map1 = om.readValue(str1, Map.class);
        System.out.println(map1.get("name"));
    }

    /**
     * Map 内嵌 List
     * @throws Exception
     */
    @Test
    public void test7() throws Exception {
        Map<String, List<String>> m1 = new HashMap<>();
        List<String> l1 = new ArrayList<>();
        l1.add("a");
        l1.add("b");
        List<String> l2 = new ArrayList<>();
        l2.add("c");
        l2.add("d");
        m1.put("k1", l1);
        m1.put("k2", l2);
        ObjectMapper om = new ObjectMapper();
        System.out.println(om.writeValueAsString(m1)); // {"k1":["a","b"],"k2":["c","d"]}
    }

    @Test
    public void test7_2() throws Exception {
        String json = "{\"k1\":[\"a\",\"b\"],\"k2\":[\"c\",\"d\"]}";
        ObjectMapper om = new ObjectMapper();
        JavaType javaType = om.getTypeFactory().constructMapType(Map.class,
                om.constructType(String.class),
                om.getTypeFactory().constructType(List.class, String.class));
        Map<String, List<String>> m1 = om.readValue(json, javaType);
        System.out.println(m1.get("k1"));
    }

    @Test
    public void test8() throws Exception {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("a");
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(list1);
        Set<String> set1 = om.readValue(json, om.getTypeFactory().constructType(Set.class, String.class));
        System.out.println(json); // ["a","b","a"]
        System.out.println(set1); // [a, b]
    }
}
