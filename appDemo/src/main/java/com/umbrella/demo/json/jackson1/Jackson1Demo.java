package com.umbrella.demo.json.jackson1;

import com.umbrella.demo.json.jackson1.domain.Course;
import com.umbrella.demo.json.jackson1.domain.School;
import com.umbrella.demo.json.jackson1.domain.User;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 大洲 on 14-12-6.
 */
public class Jackson1Demo {

    private static Logger log = Logger.getLogger(Jackson1Demo.class);

    /**
     * Object to JSON
     * null字段 会显示为 null
     */
    @Test
    public void test0() {
        ObjectMapper om1 = new ObjectMapper();
        User u1 = new User();
        u1.setId(10000);
        u1.setUserName("Leah Dizon");
        u1.setBirth(new Date());
        try {
            // {"id":10000,"userName":"Leah Dizon","birth":1434527118518,"age":0,"address":null,"workAddress":null,"school":null,"courses":null}
            om1.writeValue(System.out, u1);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * JSON to Object
     * 有不识别的字段 会抛异常
     */
    @Test
    public void test01() {
        ObjectMapper om1 = new ObjectMapper();
//        om1.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 下面的方法也可以忽略不识别的字段
        om1.setDeserializationConfig(om1.getDeserializationConfig().without(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES));

        String json = "{\"id\":10000,\"userName\":\"Leah Dizon\",\"birth\":1431336541279,\"age\":0,\"address\":null,\"wifeName\":null}";
        User u1 = null;
        try {
            u1 = om1.readValue(json, User.class);
            System.out.println(u1.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Object to JSON
     * 日期处理
     * TODO null处理 怎么把 null 自动转换为 ""
     */
    @Test
    public void test1() {
        ObjectMapper om1 = new ObjectMapper();
        om1.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        om1.configure(D)
        User u1 = new User();
        u1.setId(10000);
        u1.setUserName("Leah Dizon");
        u1.setBirth(new Date());
        u1.setWorkAddress("");
        try {
//            om1.writeValue(System.out, u1);
            String json = om1.writeValueAsString(u1);
            log.info(json);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * List 生成 JSON
     */
    @Test
    public void testGenString1() {
        User u1 = new User();
        u1.setId(10000);
        u1.setUserName("Leah Dizon");
        u1.setBirth(new Date());

        User u2 = new User();
        u2.setId(10001);
        u2.setUserName("Sanada Yukimura");

        List<User> list = new ArrayList<User>();
        list.add(u1);
        list.add(u2);

        ObjectMapper om1 = new ObjectMapper();
        om1.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            log.info(om1.writeValueAsString(list));
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * json 转 List
     * 泛型的处理
     */
    @Test
    public void toList() {
        try {
            String json = "[{\"id\":10000,\"userName\":\"Leah Dizon\",\"birth\":\"2015-03-11 12:38:00\",\"age\":0,\"address\":null,\"workAddress\":null},{\"id\":10001,\"userName\":\"Sanada Yukimura\",\"birth\":null,\"age\":0,\"address\":null,\"workAddress\":null}]";
            ObjectMapper om = new ObjectMapper();
            om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            List<User> list1 = om.readValue(json, om.getTypeFactory().constructParametricType(List.class, User.class));
            for(User u : list1) {
                log.info(u.getUserName());
            }
        } catch (Exception e) {
            log.info("", e);
        }
    }

    /**
     * Object to JSON
     * 有对象成员变量
     */
    @Test
    public void testGenJson2() {
        try {
            School school = new School();
            school.setId(1);
            school.setName("Harvard");

            User user = new User();
            user.setId(1);
            user.setUserName("Hatake Kakashi");
            user.setBirth(new Date());
            user.setSchool(school);

            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(user);
            // {"id":1,"userName":"Hatake Kakashi","birth":1426052390671,"age":0,"address":null,"workAddress":null,"school":{"id":1,"name":"Harvard"}}
            log.info(json);
        } catch (Exception e) {
            log.info("", e);
        }
    }

    /**
     * JSON to Object
     * 有对象成员变量
     */
    @Test
    public void testGenObj2() {
        try {
            String json = "{\"id\":1,\"userName\":\"Hatake Kakashi\",\"birth\":1426052390671,\"age\":0,\"address\":null,\"workAddress\":null,\"school\":{\"id\":1,\"name\":\"Harvard\"}}";

            ObjectMapper om = new ObjectMapper();
            User u1 = om.readValue(json, User.class);
            log.info(u1.getUserName());
            log.info(u1.getSchool().getName());
        } catch (Exception e) {
            log.info("", e);
        }
    }

    /**
     * Object to JSON
     * 有List成员变量
     */
    @Test
    public void testGenJson3() {
        try {
            School school = new School();
            school.setId(1);
            school.setName("Harvard");

            Course c1 = new Course();
            c1.setCourseId(1);
            c1.setCourseName("Psychology");
            Course c2 = new Course();
            c2.setCourseId(2);
            c2.setCourseName("Physiology");
            List<Course> courses = new ArrayList<Course>();
            courses.add(c1);
            courses.add(c2);

            User user = new User();
            user.setId(1);
            user.setUserName("Hatake Kakashi");
            user.setBirth(new Date());
            user.setSchool(school);
            user.setCourses(courses);
            // {"id":1,"userName":"Hatake Kakashi","birth":1426052793949,"age":0,"address":null,"workAddress":null,"school":{"id":1,"name":"Harvard"},"courses":[{"courseId":1,"courseName":"Psychology"},{"courseId":2,"courseName":"Physiology"}]}
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(user);
            log.info(json);
        } catch (Exception e) {
            log.info("", e);
        }
    }

    /**
     * JSON to List
     * 有List成员变量
     */
    @Test
    public void testGenObj3() {
        try {
            String json = "{\"id\":1,\"userName\":\"Hatake Kakashi\",\"birth\":1426052793949,\"age\":0,\"address\":null,\"workAddress\":null,\"school\":{\"id\":1,\"name\":\"Harvard\"},\"courses\":[{\"courseId\":1,\"courseName\":\"Psychology\"},{\"courseId\":2,\"courseName\":\"Physiology\"}]}";
            ObjectMapper om = new ObjectMapper();
            User u1 = om.readValue(json, User.class);
            log.info(u1.getUserName());
            log.info(u1.getBirth());
            List<Course> courses = u1.getCourses();
            for(Course c : courses) {
                log.info(c.getCourseId() + "-" + c.getCourseName());
            }
        } catch (Exception e) {
            log.info("", e);
        }
    }

    /**
     * 复杂 json 解析
     */
    @Test
    public void testFromJson4() {
        try {
            String json = "{\"status\":\"true\", \"data\":{\"id\":1,\"userName\":\"Hatake Kakashi\",\"birth\":1426052390671,\"age\":0,\"address\":null,\"workAddress\":null,\"school\":{\"id\":1,\"name\":\"Harvard\"}}}";
            ObjectMapper om = new ObjectMapper();
            JsonNode jsonNode = om.readTree(json);
            System.out.println("jsonNode=" + jsonNode.asText());
            System.out.println(jsonNode.get("status").asText());
            System.out.println("jsonNode.get(\"data\")=" + jsonNode.get("data").asText());
            User u1 = om.readValue(jsonNode.get("data"), User.class);
            System.out.println(u1.getUserName());
            System.out.println(u1.getSchool().getName());
        } catch (Exception e) {
            log.info("", e);
        }
    }

    /**
     * 数组 转 json
     * @throws Exception
     */
    @Test
    public void testArrayToJson() throws Exception {
        String[] arr1 = {"a", "b", "c"};
        ObjectMapper om = new ObjectMapper();
        log.info(om.writeValueAsString(arr1)); // ["a","b","c"]
    }
}
