package com.umbrella.util;

import com.umbrella.util.http.HttpClientUtil;
import com.umbrella.util.json.GsonUtil;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudazhou on 2015/10/16.
 */
public class HttpClientUtilRESTTomcatTest {

    /**
     * 使用 tomcat 启动
     * Get 方法
     * 生成 XML
     */
    @Test
    public void test500() {
        String url = "http://localhost:9999/rs/helloEarth/helloEarthService/user/yukimura";
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

    /**
     * 使用 tomcat 启动
     * POST body
     *
     */
    @Test
    public void test601() {
        String url = "http://localhost:9999/rs/helloEarth/helloEarthService/is";
        String params = "hahahaha";
        System.out.println(HttpClientUtil.doPostBody(url, params, "utf-8", "text/plain"));
    }

    /**
     * 使用 tomcat 启动
     * POST body
     *
     */
    @Test
    public void test602() {
        String url = "http://localhost:9999/rs/helloEarth/helloEarthService/is";
        User u = new User();
        u.setId(2000);
        u.setUserName("Hatake Kakashi");
        System.out.println(HttpClientUtil.doPostBody(url, GsonUtil.toJson(u), "utf-8", MediaType.APPLICATION_JSON));
    }
}
