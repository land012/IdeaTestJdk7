package com.umbrella.grammar.crypto;

import org.junit.Test;

import java.util.Base64;

/**
 * Created by xudazhou on 2017/3/21.
 */
public class Base64JavaDemo {

    @Test
    public void test1() throws Exception {
        String str1 = "Yukimura";
        byte[] bytes1 = str1.getBytes("utf-8");
        String base64Str = Base64.getEncoder().encodeToString(bytes1);
        System.out.println(base64Str); // WXVraW11cmE=
        byte[] bytes2 = Base64.getDecoder().decode(base64Str);
        String str2 = new String(bytes2, "utf-8");
        System.out.println(str2); // Yukimura
    }
}
