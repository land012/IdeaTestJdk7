package com.umbrella.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

/**
 * Created by xudazhou on 2015/10/20.
 */
public class URLConnectionUtil {

    private static final Logger log = LoggerFactory.getLogger(URLConnectionUtil.class);

    /**
     *
     * @param url
     * @param paramsMap
     * @param charset
     * @return
     */
    public static String doPost(String url, Map<String, String> paramsMap, String charset) {
        URL reqUrl = null;
        URLConnection conn = null;
        OutputStreamWriter out = null;
        BufferedWriter bw = null;
        OutputStream os = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            reqUrl = new URL(url);
            conn = reqUrl.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
//			conn.setRequestProperty("accept-charset", charset);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");

//			out = new OutputStreamWriter(conn.getOutputStream(), charset);
//			out.write(params);
//			out.flush();

//			os = conn.getOutputStream();
//			byte[] paramsbyte = params.getBytes(charset);
//			os.write(paramsbyte);
//			os.flush();

			bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), charset));
//            bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            if(paramsMap!=null && paramsMap.size()>0) {
                StringBuilder params = new StringBuilder();
                int i=0;
                for(Map.Entry<String, String> entry : paramsMap.entrySet()) {
                    if(i==0) {
                        params.append(entry.getKey()).append("=").append(entry.getValue());
                    } else {
                        params.append("&").append(entry.getKey()).append("=").append(entry.getValue());
                    }
                    i++;
                }
                bw.write(params.toString());
                bw.flush();
            }

            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            String line = null;
            while((line=in.readLine())!=null) {
                result.append(line);
            }
        } catch (Exception e) {
            log.error("http doPost 异常了", e);
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out = null;
            }
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
        }
        return result.toString();
    }

    /**
     *
     * @param url
     * @param params
     * @param charset
     * @return
     */
    public static String doPostBody(String url, String params, String charset) {
        URL reqUrl = null;
        URLConnection conn = null;
        OutputStreamWriter out = null;
        BufferedWriter bw = null;
        OutputStream os = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            reqUrl = new URL(url);
            conn = reqUrl.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("content-type", "application/json");

            bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), charset));
//            bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(params);
            bw.flush();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            String line = null;
            while((line=in.readLine())!=null) {
                result.append(line);
            }
        } catch (Exception e) {
            log.error("http doPost 异常了", e);
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out = null;
            }
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }
        }
        return result.toString();
    }
}
