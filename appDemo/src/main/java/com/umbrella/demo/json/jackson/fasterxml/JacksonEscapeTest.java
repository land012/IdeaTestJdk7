package com.umbrella.demo.json.jackson.fasterxml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by xudazhou 2017/12/28
 */
public class JacksonEscapeTest {
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
    public void test12() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\_idea2017\\TestApp\\appDemo\\src\\main\\resources\\json_bad.txt"), "utf-8"))) {
            String line1 = br.readLine();
            System.out.println(line1);

            ObjectMapper om = new ObjectMapper();
            om.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
            Map m1 = om.readValue(line1, Map.class);
            System.out.println(m1.get("command_no"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
