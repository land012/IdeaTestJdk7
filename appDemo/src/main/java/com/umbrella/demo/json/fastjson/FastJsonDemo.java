package com.umbrella.demo.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * create by xudazhou 2017/12/21
 */
public class FastJsonDemo {

    @Test
    public void test1() {
        List<String> l1 = new ArrayList<>();
        l1.add("a");
        l1.add("b");
        l1.add("c");
        String json = JSONObject.toJSONString(l1);
        System.out.println(json); // ["a","b","c"]

        JSONArray arr = JSON.parseArray(json);
        arr.stream()
                .forEach(x -> {
                    System.out.println(x);
                });
    }

    @Test
    public void test2() {
        JSONObject jo1 = new JSONObject();
        JSONObject jo2 = new JSONObject();
        jo1.put("k1", "v1");
        jo1.put("k2", "v2");
        jo1.put("k3", 12);
        jo2.put("j1", jo1);
        jo2.put("s1", "aaa");
        System.out.println(jo2.toString()); // {"j1":{"k1":"v1","k2":"v2","k3":12},"s1":"aaa"}
        System.out.println(jo2.toJSONString());
    }

    /**
     * 字段用下划线分隔 驼峰
     * 未实现
     */
    @Test
    public void test3() {
        User u1 = new User();
        u1.setuId(1);
        u1.setUserName("tom");
        String json = JSON.toJSONString(u1);
        System.out.println(json); // {"user_id":1,"user_nameee":"tom"}
        /**
         * {
         "user_id":1,
         "user_nameee":"tom"
         }
         */
        System.out.println(JSON.toJSONString(u1, true));

        User u2 = JSON.parseObject(json, User.class, Feature.AllowUnQuotedFieldNames);
        System.out.println(u2);
    }

    /**
     * 格式异常的json
     */
    @Test
    public void test4() {
//        File f = new File(".");
//        System.out.println(f.getAbsolutePath());

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\_idea2017\\TestApp\\appDemo\\src\\main\\resources\\json_bad.txt"), "utf-8"))) {
            String line1 = br.readLine();
            System.out.println(line1);

            JSONObject jo = JSON.parseObject(line1, Feature.AutoCloseSource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
