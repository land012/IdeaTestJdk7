package com.umbrella.util;

import com.umbrella.util.http.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudazhou on 2015/10/16.
 */
public class HttpClientUtilRESTServerTest {

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
     * REST
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

}
