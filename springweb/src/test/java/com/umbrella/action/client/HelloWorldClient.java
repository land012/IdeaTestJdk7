package com.umbrella.action.client;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.springframework.http.RequestEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 大洲 on 15-1-27.
 */
public class HelloWorldClient {
    public static void main(String[] args) {
        CloseableHttpClient http = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:9999/springmvc/hello");
        try {
            httpPost.addHeader("k1", "v1");
            HttpEntity reqEntity = new StringEntity("你好", "utf-8");
            httpPost.setEntity(reqEntity);

            CloseableHttpResponse resp = http.execute(httpPost);

            HttpEntity entity = resp.getEntity();
            InputStream is = entity.getContent();
            byte[] arr = new byte[1024];
            int i = -1;
            StringBuilder res = new StringBuilder();
            while ((i=is.read(arr))!=-1) {
                res.append(new String(arr, 0, i));
            }
            System.out.println(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        CloseableHttpClient http = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:9999/springmvc/hello");
        try {
            httpPost.addHeader("k1", "v1");

            CloseableHttpResponse resp = http.execute(httpPost);

            HttpEntity entity = resp.getEntity();
            InputStream is = entity.getContent();
            byte[] arr = new byte[1024];
            int i = -1;
            StringBuilder res = new StringBuilder();
            while ((i=is.read(arr))!=-1) {
                res.append(new String(arr, 0, i));
            }
            System.out.println(res.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
