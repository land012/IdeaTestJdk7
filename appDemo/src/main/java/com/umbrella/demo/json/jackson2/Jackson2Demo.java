package com.umbrella.demo.json.jackson2;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
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
     */
    @Test
    public void test2IgnoreUnknownProperties() {
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
    public void test3Jsontolist() {
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
     * BOM开关的字符串
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
     * 解析通过
     */
    @Test
    public void test10_controllerchar_1() throws IOException {
        // json 实际存的值是“["\u7535\t\u4FE1"]”
        String json = "[\"\\u7535\\t\\u4FE1\"]";
        ObjectMapper om = new ObjectMapper();
//        om.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        List<String> list1 = om.readValue(json, om.getTypeFactory().constructCollectionType(List.class, String.class));
        // 实际解析的结果是 “电\t信”
        // 只是打印到控制台时 \t 自动为了 制表符
        System.out.println(list1); // [电	信]
    }

    /**
     * 解析失败
     * 因为有控制字符 \t，解析失败
     */
    @Test
    public void test10_controllerchar_1_1() throws IOException {
        String json = "[\"\u7535\t\u4FE1\"]";
        ObjectMapper om = new ObjectMapper();
//        om.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        List<String> list1 = om.readValue(json, om.getTypeFactory().constructCollectionType(List.class, String.class));
        System.out.println(list1); // [电	信]
    }

    /**
     * json解析失败
     * com.fasterxml.jackson.databind.JsonMappingException:
     * Illegal unquoted character ((CTRL-CHAR, code 9)): has to be escaped using backslash to be included in string value
     */
    @Test
    public void test10_controllerchar_2() throws IOException {
        String json = "[\"\\u7535\\t\\u4FE1\"]";
        // 加了这句，解析不过了
        String json2 = StringEscapeUtils.unescapeJson(json);
        // json2的实际值是“["电\t信"]”
        // 所以解析会失败
        System.out.println(json2); // ["电	信"]
        ObjectMapper om = new ObjectMapper();
        List<String> list1 = om.readValue(json2, om.getTypeFactory().constructCollectionType(List.class, String.class));
        System.out.println(list1);
    }

    /**
     * 解析成功
     */
    @Test
    public void test10_controllerchar_3() throws IOException {
        String json = "[\"\\u7535\\t\\u4FE1\"]";
        String json2 = StringEscapeUtils.unescapeJson(json);
        System.out.println(json2); // ["电	信"]
        ObjectMapper om = new ObjectMapper();
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        List<String> list1 = om.readValue(json2, om.getTypeFactory().constructCollectionType(List.class, String.class));
        System.out.println(list1); // [电	信]
    }

    /***
     * 转义字符
     * @throws Exception
     */
    @Test
    public void test11_escape() throws Exception {
        Map<String, String> map1 = new HashMap<>();
        map1.put("k1", "\\u7535\\u4FE1");
        ObjectMapper om = new ObjectMapper();
        String json1 = om.writeValueAsString(map1);
        System.out.println(json1); // {"k1":"\\u7535\\u4FE1"}

        Map<String, String> map2 = om.readValue(json1, om.getTypeFactory().constructMapType(Map.class, String.class, String.class));
        System.out.println(map2); // {k1=\u7535\u4FE1}
    }

    @Test
    public void test11_escape_1_1() throws Exception {
        String json1 = "{\"k1\":\"\\u7535\\u4FE1\"}";
        ObjectMapper om = new ObjectMapper();
        Map<String, String> map2 = om.readValue(json1, om.getTypeFactory().constructMapType(Map.class, String.class, String.class));
        System.out.println(map2); // {k1=电信}
    }

    /***
     * 转义字符
     * @throws Exception
     */
    @Test
    public void test11_escape_2() throws Exception {
        Map<String, String> map1 = new HashMap<>();
        map1.put("k1", "\u7535\u4FE1"); // 这个时候，map中存的就是 "k1"->"电信"
        ObjectMapper om = new ObjectMapper();
        String json1 = om.writeValueAsString(map1);
        System.out.println(json1); // {"k1":"电信"}

        Map<String, String> map2 = om.readValue(json1, om.getTypeFactory().constructMapType(Map.class, String.class, String.class));
        System.out.println(map2); // {k1=电信}
    }

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
}
