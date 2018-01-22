package com.umbrella.demo.json.jackson.fasterxml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.umbrella.demo.json.vo.Course;
import com.umbrella.vo.User;
import org.apache.commons.lang3.StringEscapeUtils;
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
     * 把中文转成 unicode
     * JSON - Object
     *
     */
    @Test
    public void test0() {
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("Alphonse Elric");
        u1.setBirthDay(new Date());
        u1.setGender(0);
        u1.setUrgencyContactName("小张");
        ObjectMapper om = new ObjectMapper();
        om.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); // Date类型转化为 JSON 时的样式
        try {
            // 生成JSON串
            String str1 = om.writeValueAsString(u1);
            // {"id":1,"userName":"Alphonse Elric","birthDay":"2017-12-27 16:23:03","gender":0,"urgencyContactName":"\u5C0F\u5F20","age":null,"enrolDate":null}
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
    public void test1Objtojson()  {
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
     * null 字段不序列化??
     * null 字段默认会序列化为 null
     * 怎样不序列化该字段??
     * @throws Exception
     */
    @Test
    public void test12Objtojson() throws Exception {
        String str1 = StringEscapeUtils.escapeJava("小赵");
        System.out.println(str1);

        User u1 = new User();
        u1.setUserName(str1);

        ObjectMapper om = new ObjectMapper();
        // 不序列化 null 字段
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // {"id":0,"userName":"\\u5C0F\\u8D75","gender":0}
        System.out.println(om.writeValueAsString(u1));
    }

    /**
     * List 生成 JSON
     */
    @Test
    public void test2Listtojson()  {
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
     * 对象中存在，json中不存在的字段，不会反序列化，且不会报异常，
     * 对象值为 null
     * 对于基本类型，如 int gender，如果 json 中不存在，那么不会反序列化该字段，也不会报异常，对象会用相应类型的默认值(int 0)
     * 对于 Integer 类型，则为 null
     */
    @Test
    public void test2IgnoreUnknownProperties() {
        try {
            String str1 = "{\"id\":1,\"userName\":\"Alphonse Elric\",\"birthDay\":\"2015-01-07 11:10:52\",\"address\":\"Bei Jing\"}";
            ObjectMapper om = new ObjectMapper();
            om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 忽略字符串中不识别的属性
            User u1 = om.readValue(str1, User.class);
            // User[id=1,userName=Alphonse Elric,birthDay=Wed Jan 07 11:10:52 CST 2015,gender=0,urgencyContactName=<null>,age=<null>,enrolDate=<null>]
            System.out.println(u1.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析 json 为 List
     * 构建 泛型类
     */
    @Test
    public void test3Jsontolist() {
        try {
            String json1 = "[{\"id\":1,\"userName\":\"Alphonse Elric\",\"birthDay\":\"2015-03-11 11:37:16\",\"gender\":0,\"urgencyContactName\":\"Edward\"},{\"id\":2,\"userName\":\"Leah Dizon\",\"birthDay\":\"2015-03-11 11:37:16\",\"gender\":1,\"urgencyContactName\":\"Oda Nobunaga\"}]";
            ObjectMapper om = new ObjectMapper();
            om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            List<User> list1 = om.readValue(json1, om.getTypeFactory().constructCollectionType(List.class, User.class));
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
     * 复杂 json 解析: 对象中包含对象
     * 获取 data Node
     */
    @Test
    public void test4Jsontomap() throws Exception {
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
    public void test5UnwrapRoot() throws Exception {
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
    public void test5_nulltojson() throws Exception {
        ObjectMapper om = new ObjectMapper();
        String str1 = om.writeValueAsString(null);
        System.out.println("str1=" + str1); // str1=null
        if (str1 == null) {
            System.out.println("str1 == null");
        } else {
            System.out.println("str1 is null"); // str1 is null
        }
    }

    /**
     * BOM 开关的字符串
     */
    @Test
    public void test6_bom() throws Exception {
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
    public void test7_maplist2json() throws Exception {
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

    /**
     * 构建 泛型类
     * @throws Exception
     */
    @Test
    public void test7_typefactory() throws Exception {
        String json = "{\"k1\":[\"a\",\"b\"],\"k2\":[\"c\",\"d\"]}";
        ObjectMapper om = new ObjectMapper();
        JavaType javaType = om.getTypeFactory().constructMapType(Map.class,
                om.constructType(String.class),
                om.getTypeFactory().constructType(List.class, String.class));
        Map<String, List<String>> m1 = om.readValue(json, javaType);
        System.out.println(m1.get("k1"));
    }

    /**
     * json to set
     * 有重复值
     * @throws Exception
     */
    @Test
    public void test8_listjson2set() throws Exception {
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

    /**
     * key 是 int类型
     */
    @Test
    public void test9_keyISint() throws Exception {
        ObjectMapper om = new ObjectMapper();
        Map<Object, Object> map1 = new HashMap<>();
        map1.put(1, "a");
        map1.put(2, "b");
        String json = om.writeValueAsString(map1);
        System.out.println(json); // {"1":"a","2":"b"}
    }

    /**
     * key 是 int类型
     * json 中的 key 须用双引号引起来，否则解析报错
     * com.fasterxml.jackson.core.JsonParseException: Unexpected character ('1' (code 49)): was expecting double-quote to start field name
     at [Source: {1:"a",2:"b"}; line: 1, column: 3]
     * @throws Exception
     */
    @Test
    public void test9_2() throws Exception {
        String json2 = "{1:\"a\",2:\"b\"}";

        ObjectMapper om = new ObjectMapper();
        Map<Object, Object> map2 = om.readValue(json2, Map.class);
        System.out.println(map2);
    }

    /**
     * key 是 int类型
     * 这样不会报错
     * 允许key 不用引号
     * @throws Exception
     */
    @Test
    public void test9_3() throws Exception {
        String json2 = "{1:\"a\",2:\"b\"}";

        ObjectMapper om = new ObjectMapper();
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        Map<Object, Object> map2 = om.readValue(json2, Map.class);
        System.out.println(map2);
    }

    /**
     * 最外层是 List
     * List 中含不同类型的对象
     * @throws Exception
     */
    @Test
    public void test12() throws Exception {
        List<Object> list1 = new ArrayList<>();
        Course c1 = new Course();
        c1.setCourseId(1);
        c1.setCourseName("chinese");
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");
        list1.add(c1);
        list1.add(list2);

        ObjectMapper om = new ObjectMapper();
        String json1 = om.writeValueAsString(list1);
        System.out.println(json1); // [{"courseId":1,"courseName":"chinese"},["a","b"]]
    }

    /**
     * java 对象是驼峰标识
     * json 字段是下划线
     * @throws JsonProcessingException
     */
    @Test
    public void test13_camel() throws IOException {
        com.umbrella.demo.json.jackson.fasterxml.User u1 = new com.umbrella.demo.json.jackson.fasterxml.User();
        u1.setuId(2);
        u1.setUserName("tom");
        Map<String, Double> hobby = new HashMap<>();
        hobby.put("fishiing", 9d);
        hobby.put("readding", 10d);
        u1.setHobbyPref(hobby);
        ObjectMapper om = new ObjectMapper();
        om.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        String json1 = om.writeValueAsString(u1);
        System.out.println(json1); // {"u_id":2,"user_name":"tom","hobby_pref":{"readding":10.0,"fishiing":9.0}}

        String json2 = "{\"u_id\":3,\"user_name\":\"jim\",\"hobby_pref\":{\"eat\":1.2,\"game\":4.2}}";
        com.umbrella.demo.json.jackson.fasterxml.User u2 = om.readValue(json2, com.umbrella.demo.json.jackson.fasterxml.User.class);
        System.out.println(u2); // User[uId=3,userName=jim,hobbyPref={eat=1.2, game=4.2}]
    }
}
