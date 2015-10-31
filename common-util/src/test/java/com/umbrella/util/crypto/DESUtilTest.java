package com.umbrella.util.crypto;

import org.junit.Test;

/**
 * Created by xudazhou on 2015/10/22.
 */
public class DESUtilTest {
    @Test
    public void test1() throws Exception {
        String plaintext = "abc";
        String desKey = "1234567890"; // desKey 长度大于8位
        DESUtil desUtil = new DESUtil(desKey);
        System.out.println(desUtil.encryptStr(plaintext, "utf-8")); // dZCJpcaeSoI=
        System.out.println(desUtil.encryptStr(plaintext, "utf-8")); // dZCJpcaeSoI=

        System.out.println(DESUtil2.encrypt(plaintext, desKey, "utf-8"));  // wkuY+tXAWA4=
        System.out.println(DESUtil2.encrypt2(plaintext, desKey, "utf-8")); // wkuY+tXAWA4=

        DESPlus desPlus = new DESPlus(desKey, "utf-8");
        System.out.println(desPlus.encrypt(plaintext)); // c24b98fad5c0580e
        // DESUtil2 与 DESPlus 的密钥生成方式是相同的，只是由 byte[] 转 String 的方式不同
        System.out.println(desPlus.encrypt2(plaintext)); // wkuY+tXAWA4=
    }
}
