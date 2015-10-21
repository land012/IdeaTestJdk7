package com.umbrella.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudazhou on 2015/10/16.
 */
public class HttpClientUtilSpringMVCTest {
    /**
     * SpringMVC
     * POST
     */
    @Test
    public void test1() {
        String url = "http://localhost:8080/springmvc/hello";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id", "1001");
        paramMap.put("userName", "Sanada");
        System.out.println(HttpClientUtil.doPost(url, paramMap, "utf-8"));
    }

    /**
     * SpringMVC
     * POST
     */
    @Test
    public void test2() {
        String url = "http://localhost:8080/springmvc/hello";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("id", "1001");
        paramMap.put("userName", "Sanada");
        System.out.println(HttpClientUtil.doPost2(url, paramMap, "utf-8"));
    }

    /**
     * SpringMVC
     * POST BODY
     */
    @Test
    public void test3_1() {
        String url = "http://localhost:8080/springmvc/hello";
        User u = new User();
        u.setId(1111);
        u.setUserName("Yukimura");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

        System.out.println(HttpClientUtil.doPostBody(url, gson.toJson(u), "utf-8", "application/json"));
    }

    /**
     * POST BODY
     * 即使是 & 分割，request.getParamter() 仍然获取不到
     */
    @Test
    public void test3_2() {
        String url = "http://localhost:8080/springmvc/hello";
        String str = "id=1112&userName=Sanada";
        System.out.println(HttpClientUtil.doPostBody(url, str, "utf-8", "application/x-www-form-urlencoded"));
    }

    /**
     * POST BODY
     * request.getParamter() 可以取到
     */
    @Test
    public void test3_3() {
        String url = "http://localhost:8080/springmvc/greet";
        String str = "id=1112&userName=Sanada";
        System.out.println(HttpClientUtil.doPostBody(url, str, "utf-8", "application/x-www-form-urlencoded"));
    }

    /**
     * REST Http
     * 使用自己写的 Sever 启动
     * Get
     * 响应 XML
     */
    @Test
    public void test400() {
        String url = "http://localhost:8080/rs/helloEarth/user/yukimura";
        System.out.println(HttpClientUtil.doGet(url, "utf-8"));
    }

    /**
     * 使用自己写的 Sever 启动
     * POST
     * 响应 JSON
     */
    @Test
    public void test401() {
        String url = "http://localhost:8080/rs/helloEarth/student";
        Map<String, String> params = new HashMap<>();
        params.put("id", "100");
        System.out.println(HttpClientUtil.doPost(url, params, "utf-8"));
    }

    /**
     * 使用自己写的 Sever 启动
     * GET
     * 响应 JSON
     */
    @Test
    public void test402() {
        String url = "http://localhost:8080/rs/helloEarth/student2?stuName=Kakashi";
        System.out.println(HttpClientUtil.doGet(url, "utf-8"));
    }

    /**
     * 使用 tomcat 启动
     * Get 方法
     * 生成 XML
     */
    @Test
    public void test500() {
        String url = "http://localhost:9999/rs/helloEarth/helloEarth/user/yukimura";
        System.out.println(HttpClientUtil.doGet(url, "utf-8"));
    }

    /**
     * 使用 tomcat 启动
     * POST 方法
     * 响应 JSON
     */
    @Test
    public void test501() {
        String url = "http://localhost:9999/rs/helloEarth/helloEarthService/student";
        Map<String, String> params = new HashMap<>();
        params.put("id", "100");
        System.out.println(HttpClientUtil.doPost(url, params, "utf-8"));
    }

    /**
     * 使用 tomcat 启动
     * GET 方法
     * 响应 JSON
     */
    @Test
    public void test502() {
        String url = "http://localhost:9999/rs/helloEarth/helloEarthService/getStudentByName?stuName=Kakashi";
        System.out.println(HttpClientUtil.doGet(url, "utf-8"));
    }
}
