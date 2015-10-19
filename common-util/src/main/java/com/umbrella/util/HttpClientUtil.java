package com.umbrella.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by xudazhou on 2015/10/16.
 */
public class HttpClientUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * NameValuePair
     * @param url
     * @param paramMap
     * @param charset
     * @return
     */
    public static String doPost(String url, Map<String, String> paramMap, String charset) {
        HttpClient httpClient = null;
        PostMethod postMethod = null;
        try {
            httpClient = new HttpClient();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(5000);

            postMethod = new PostMethod(url);
            postMethod.getParams().setContentCharset(charset);
            if(paramMap!=null && paramMap.size()>0) {
                NameValuePair[] pairs = new NameValuePair[paramMap.size()];
                int i = 0;
                for(Map.Entry<String, String> entry : paramMap.entrySet()) {
                    pairs[i] = new NameValuePair(entry.getKey(), entry.getValue());
                    i++;
                }
                postMethod.addParameters(pairs); // request.getParameter(String) 可以取到参数
            }

            int httpStatus = httpClient.executeMethod(postMethod);
            log.info("doPost httpStatus=" + httpStatus);
            if(httpStatus== HttpStatus.SC_OK) {
                return postMethod.getResponseBodyAsString();
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if(postMethod!=null) {
                postMethod.releaseConnection();
            }
        }
        return null;
    }

    /**
     * addParameter
     * @param url
     * @param paramMap
     * @param charset
     * @return
     */
    public static String doPost2(String url, Map<String, String> paramMap, String charset) {
        HttpClient httpClient = null;
        PostMethod postMethod = null;
        try {
            httpClient = new HttpClient();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(5000);

            postMethod = new PostMethod(url);
            postMethod.getParams().setContentCharset(charset);

            postMethod.addRequestHeader("h1", "head1"); // request.getHeader(String) 可以取到参数

            if(paramMap!=null && paramMap.size()>0) {
                for(Map.Entry<String, String> entry : paramMap.entrySet()) {
                    postMethod.addParameter(entry.getKey(), entry.getValue()); // request.getParameter(String) 可以取到参数
                }
            }

            int httpStatus = httpClient.executeMethod(postMethod);
            log.info("doPost httpStatus=" + httpStatus);
            if(httpStatus== HttpStatus.SC_OK) {
                return postMethod.getResponseBodyAsString();
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if(postMethod!=null) {
                postMethod.releaseConnection();
            }
        }
        return null;
    }

    /**
     * StringRequestEntity
     * @param url
     * @param paramJson
     * @param charset
     * @return
     */
    public static String doPost3(String url, String paramJson, String charset) {
        HttpClient httpClient = null;
        PostMethod postMethod = null;
        try {
            httpClient = new HttpClient();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(5000);

            postMethod = new PostMethod(url);
            postMethod.getParams().setContentCharset(charset);

            // 这个不是这样用的
//            HttpMethodParams methodParams = new HttpMethodParams();
//            methodParams.setParameter("h1", "head1");
//            postMethod.setParams(methodParams);

            RequestEntity requestEntity = new StringRequestEntity(paramJson, "application/json", charset);
            postMethod.setRequestEntity(requestEntity); // request.getParameter(String) 取不到参数，request.getInputStream 才能取到参数

            int httpStatus = httpClient.executeMethod(postMethod);
            log.info("doPost httpStatus=" + httpStatus);
            if(httpStatus== HttpStatus.SC_OK) {
                return postMethod.getResponseBodyAsString();
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if(postMethod!=null) {
                postMethod.releaseConnection();
            }
        }
        return null;
    }

    /**
     *
     * @param url
     * @param charset
     * @return
     */
    public static String doGet(String url, String charset) {
        HttpClient httpClient = null;
        GetMethod getMethod = null;
        try {
            httpClient = new HttpClient();
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(5000);

            getMethod = new GetMethod(url);
            getMethod.getParams().setContentCharset(charset);

            int httpStatus = httpClient.executeMethod(getMethod);
            log.info("doPost httpStatus=" + httpStatus);
            if(httpStatus== HttpStatus.SC_OK) {
                return getMethod.getResponseBodyAsString();
            }
        } catch (Exception e) {
            log.error("", e);
        } finally {
            if(getMethod!=null) {
                getMethod.releaseConnection();
            }
        }
        return null;
    }
}
