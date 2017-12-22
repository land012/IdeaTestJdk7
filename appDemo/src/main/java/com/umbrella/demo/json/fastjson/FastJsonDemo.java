package com.umbrella.demo.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

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
}
