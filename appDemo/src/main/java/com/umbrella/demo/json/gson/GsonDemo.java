package com.umbrella.demo.json.gson;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;
import com.umbrella.demo.json.jackson.codehaus.domain.Data;
import com.umbrella.demo.json.jackson.codehaus.domain.User;
import com.umbrella.demo.json.vo.Result;
import com.umbrella.demo.json.vo.School;
import com.umbrella.demo.json.vo.Student;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 大洲 on 15-3-11.
 */
public class GsonDemo {

    private static Logger log = Logger.getLogger(GsonDemo.class);

    /**
     * Object to JSON
     * 入门
     * 1. 字段为 null，不会生成到 json中，生成 Json时，会根据成员变量去生成的，而不是get方法，比如 get方法返回了值，但生成的json时，值仍为 null
     * 2. 特殊字符，如=，会转换为 unicode编码
     */
    @Test
    public void testToJson() {
        Student s1 = new Student();
        s1.setSno(1);
        s1.setName("Uchiha = Sasuke");
        s1.setBirth(new Date());

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .disableHtmlEscaping() // 加上这个后，不会 escapseJava
                .create();

        String json = gson.toJson(s1);
        // 默认会进行 escapseJava
        log.info(json); // {"sno":1,"name":"Uchiha \u003d Sasuke","birth":"2015-07-01 10:13:31"}
        log.info(StringEscapeUtils.unescapeJava(json));
    }

    /**
     * Object to JSON
     * 字段为 null，会生成到 json中
     */
    @Test
    public void testToJson11() {
        Student s1 = new Student();
        s1.setSno(1);
        s1.setName("Uchiha Sasuke");
        s1.setBirth(new Date());

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();
        // {"sno":1,"name":"Uchiha Sasuke","birth":"2015-03-11 17:27:53","school":null,"address":null}
        log.info(gson.toJson(s1));
    }

    /**
     * JSON to Object
     * 入门
     */
    @Test
    public void testFromJson() {
        String json = "{\"sno\":1,\"name\":\"Uchiha Sasuke\",\"birth\":\"2015-03-11 14:21:30.789\"}";
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        Student s1 = gson.fromJson(json, Student.class);
        log.info(s1.getName());
        log.info(s1.getBirth());
    }

    @Test
    public void testFromJson01() {
        String json = "{\"sno\":1,\"name\":\"Uchiha Sasuke\",\"birth\":\"2015-03-11 14:21:30.789\"}";
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
        System.out.println(jsonObj.get("sno"));
        System.out.println(jsonObj.get("name").getAsString());
    }

    /**
     * JSON to List
     * 列表，有一个对象 [{}]
     * 使用下面的方法解析， 会抛异常
     * com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was BEGIN_ARRAY at line 1 column 2 path $
     */
    @Test
    public void testFromJson11() {
        // 这种 json 返回 List
        String json = "[{\"sno\":1,\"name\":\"Uchiha Sasuke\",\"birth\":\"2015-03-11 14:21:30.789\"}]";
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        Student s1 = gson.fromJson(json, Student.class);
        log.info(s1.getName());
        log.info(s1.getBirth());
    }

    /**
     * JSON to Object
     * 有不识别的属性
     * 会自动过滤掉不识别的属性
     * TODO 怎么在出现不识别的属性时抛异常？？？
     */
    @Test
    public void testFromJson12() {
        // 这种 json 返回 List
        String json = "{\"sno\":1,\"name\":\"Uchiha Sasuke\",\"birth\":\"2015-03-11 14:21:30.789\", \"gender\":\"male\"}";
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        Student s1 = gson.fromJson(json, Student.class);
        log.info(s1.getName());
        log.info(s1.getBirth());
    }

    /**
     * List to JSON
     */
    @Test
    public void testToJson2() {
        List<Student> students = new ArrayList<Student>();
        School school = new School();
        school.setId(1);
        school.setName("Harvard");

        Student s1 = new Student();
        s1.setSno(1);
        s1.setName("Uchiha Sasuke");
        s1.setBirth(new Date());
        s1.setSchool(school);

        Student s2 = new Student();
        s2.setSno(2);
        s2.setName("Mitarashi Anko");
        s2.setBirth(new Date());
        s2.setSchool(school);

        students.add(s1);
        students.add(s2);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        // [{"sno":1,"name":"Uchiha Sasuke","birth":"2015-03-11 14:29:22","school":{"id":1,"name":"Harvard"}},{"sno":2,"name":"Mitarashi Anko","birth":"2015-03-11 14:29:22","school":{"id":1,"name":"Harvard"}}]
        log.info(gson.toJson(students));
    }

    /**
     * JSON to List
     */
    @Test
    public void testFromJson2() {
        String json = "[{\"sno\":1,\"name\":\"Uchiha Sasuke\",\"birth\":\"2015-03-11 14:29:22\",\"school\":{\"id\":1,\"name\":\"Harvard\"}},{\"sno\":2,\"name\":\"Mitarashi Anko\",\"birth\":\"2015-03-11 14:29:22\",\"school\":{\"id\":1,\"name\":\"Harvard\"}}]";
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        List<Student> students = gson.fromJson(json, new TypeToken<List<Student>>(){}.getType());
        for(Student s : students) {
            log.info(s.getName());
        }
    }

    /**
     * JSON to List 2
     */
    @Test
    public void testFromJson22() {
        String json = "[{\"sno\":1,\"name\":\"Uchiha Sasuke\",\"birth\":\"2015-03-11 14:29:22\",\"school\":{\"id\":1,\"name\":\"Harvard\"}},{\"sno\":2,\"name\":\"Mitarashi Anko\",\"birth\":\"2015-03-11 14:29:22\",\"school\":{\"id\":1,\"name\":\"Harvard\"}}]";
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        JsonArray jsonArray = gson.fromJson(json, JsonArray.class);
        System.out.println(jsonArray.get(0).getAsJsonObject().get("name").getAsString()); // Uchiha Sasuke
    }

    /**
     * JSON to List 3
     */
    @Test
    public void testFromJson23() {
        String json = "[{\"sno\":1,\"name\":\"Uchiha Sasuke\",\"birth\":\"2015-03-11 14:29:22\",\"school\":{\"id\":1,\"name\":\"Harvard\"}},{\"sno\":2,\"name\":\"Mitarashi Anko\",\"birth\":\"2015-03-11 14:29:22\",\"school\":{\"id\":1,\"name\":\"Harvard\"}}]";
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        System.out.println(jsonElement.getAsJsonArray().get(1).getAsJsonObject().get("name").getAsString()); // Mitarashi Anko
    }

    /**
     * 复杂 Json解析
     * 获取 data Node
     */
    @Test
    public void testFromJson3() {
        String json = "{\"status\":\"true\", \"data\":{\"sno\":1,\"name\":\"Uchiha Sasuke\",\"birth\":\"2015-03-11 14:29:22\",\"school\":{\"id\":1,\"name\":\"Harvard\"}}}";
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(json);
        JsonObject object = element.getAsJsonObject();
        JsonElement status = object.get("status");
        System.out.println(status.getAsString());
        JsonObject data = object.getAsJsonObject("data");
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        Student s1 = gson.fromJson(data, Student.class);
        System.out.println(s1.getName());
        System.out.println(s1.getSchool().getName());
    }

    /**
     * 序列化的对象有类成员变量
     * 值为 null 的成员变量也会序列化
     */
    @Test
    public void testToJson4() {
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("Hatake Kakashi");
        Data data = new Data();
        data.setUser(u1);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();
        // {"user":{"id":1,"userName":"Hatake Kakashi","birth":null,"age":0,"workAddress":null,"school":null,"courses":null},"lion":null}
        System.out.println(gson.toJson(data));
    }

    /**
     * 反序列
     */
    @Test
    public void testFromJson4() {
        String json = "{\"user\":{\"id\":1,\"userName\":\"Hatake Kakashi\",\"birth\":null,\"age\":0,\"address\":null,\"workAddress\":null,\"school\":null,\"courses\":null},\"lion\":null}";
        JsonParser parser = new JsonParser();
        JsonElement eleRoot = parser.parse(json);
        JsonObject objRoot = eleRoot.getAsJsonObject();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        boolean hasLion = objRoot.has("lion");
        System.out.println("hasLion=" + hasLion); // true
        if(hasLion) {
            JsonElement eleLion = objRoot.get("lion");
            System.out.println("eleLion=" + eleLion);
            boolean nullLion = eleLion.isJsonNull();
            System.out.println("nullLion=" + nullLion);
            if(!nullLion) {
                System.out.println("lion");
            }
        }

        boolean hasUser = objRoot.has("user");
        System.out.println("hasUser=" + hasUser);
        if(hasUser) {
            JsonElement eleUser = objRoot.get("user");
            boolean nullUser = eleUser.isJsonNull();
            System.out.println("nullUser=" + nullUser);
            if(!nullUser) {
                User u1 = gson.fromJson(eleUser, User.class);
                System.out.println("u1.getUserName()=" + u1.getUserName());
            }
        }


        // java.lang.ClassCastException: com.google.gson.JsonNull cannot be cast to com.google.gson.JsonObject
//        JsonObject objLion = obj.getAsJsonObject("lion");
//        System.out.println("objLion=" + objLion);
//

//        Lion lion1 = gson.fromJson(objLion, Lion.class);
//        System.out.println("lion1=" + lion1);
    }

    /**
     * 泛型
     */
    @Test
    public void test8() {
        Result<User> result = new Result<>();
        User u1 = new User();
        u1.setUserName("Shibata Katsuie");
        result.setSuccess(true);
        result.setT(u1);
        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(result)); // {"success":true,"t":{"id":0,"userName":"Shibata Katsuie","age":0}}
    }

    /**
     * transient关键字 address没有被序列化
     * static 的成员变量也不能被序列化
     */
    @Test
    public void test9() {
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("Susanoo");
        u1.setAddress("Beijing");
        u1.setWorkAddress("BJ");
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();
        // {"id":1,"userName":"Susanoo","birth":null,"age":0,"school":null,"courses":null}
        System.out.println(gson.toJson(u1));
    }
}
