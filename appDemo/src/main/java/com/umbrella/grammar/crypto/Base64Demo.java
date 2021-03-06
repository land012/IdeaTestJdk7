package com.umbrella.grammar.crypto;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Created by xudazhou on 2015/10/16.
 */
public class Base64Demo {
    /**
     * Java Base64
     */
    @Test
    public void test1() {
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            String str1 = "Yukimura";
            String base64Str = encoder.encode(str1.getBytes("utf-8"));
            System.out.println(base64Str); // WXVraW11cmE=
            BASE64Decoder decoder = new BASE64Decoder();
            String str2 = new String(decoder.decodeBuffer(base64Str), "UTF-8");
            System.out.println(str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * apache codec
     */
    @Test
    public void test2() {
        String str1 = "Yukimura";
        try {
            byte[] bytes1 = str1.getBytes("utf-8");
            String base64Str1 = Base64.encodeBase64String(bytes1);
            System.out.println(base64Str1); // WXVraW11cmE=
            String base64Str2 = Base64.encodeBase64URLSafeString(bytes1);
            System.out.println(base64Str2); // WXVraW11cmE
            byte[] bytes2 = Base64.decodeBase64(base64Str2);
            System.out.println(new String(bytes2, "utf-8")); // Yukimura
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * isChunked
     */
    @Test
    public void test3() {
        String str1 = "Yukimura";
        try {
            System.out.println(new String(Base64.encodeBase64(str1.getBytes("utf-8")), "utf-8"));       // WXVraW11cmE=
            System.out.println(new String(Base64.encodeBase64(str1.getBytes("utf-8"), true), "utf-8")); // WXVraW11cmE=
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() throws Exception {
        System.out.println(Base64.encodeBase64String("123456789abcdefg".getBytes("utf-8")));
        System.out.println(new String(Base64.decodeBase64("MTIzNDU2Nzg5MA=="), "utf-8")); // 1234567890
    }
}
