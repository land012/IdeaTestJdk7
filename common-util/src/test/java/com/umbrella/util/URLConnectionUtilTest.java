package com.umbrella.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudazhou on 2015/10/20.
 */
public class URLConnectionUtilTest {
    /**
     *
     */
    @Test
    public void testDoPost1_1() {
        String url = "http://localhost:8080/springmvc/hello";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id", "1001");
        paramMap.put("userName", "Sanada");
        System.out.println(URLConnectionUtil.doPost(url, paramMap, "utf-8"));
    }

    /**
     * doPost body
     */
    @Test
    public void testDoPost2_1() {
        String url = "http://localhost:8080/springmvc/hello";
        User u = new User();
        u.setId(1111);
        u.setUserName("Yukimura");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        System.out.println(URLConnectionUtil.doPostBody(url, gson.toJson(u), "utf-8"));
    }
}
