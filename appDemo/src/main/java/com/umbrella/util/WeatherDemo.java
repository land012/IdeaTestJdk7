package com.umbrella.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.opensaml.ws.soap.common.SOAPObject;
import org.opensaml.ws.soap.common.SOAPObject;
import org.opensaml.xml.Namespace;
import org.opensaml.xml.NamespaceManager;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.schema.XSBooleanValue;
import org.opensaml.xml.util.IDIndex;
import org.opensaml.xml.validation.ValidationException;
import org.opensaml.xml.validation.Validator;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

/**
 * Created by 大洲 on 15-6-14.
 * 获取天气 webxml.com.cn
 */
public class WeatherDemo {

    /**
     * Http
     * 必须传 theUserID，否则返回失败
     * 但是在 浏览器中直接访问，不传 theUserID不会失败？？
     */
    @Test
    public void test1() {
        try {
            String url = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx/getWeather?theCityCode=滕州&theUserID=bb7ccbac2df247da94c48c9f6d87d940";
            HttpGet request = new HttpGet(url);
            HttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(request);
            InputStream is = response.getEntity().getContent();
            byte[] buffer = new byte[1024];
            int i=-1;
            StringBuilder res = new StringBuilder();
            while((i=is.read(buffer))!=-1) {
                res.append(new String(buffer, 0, i, "utf-8"));
            }
            System.out.println(res.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * SOAP
     */
    @Test
    public void test2() {
    }
}
