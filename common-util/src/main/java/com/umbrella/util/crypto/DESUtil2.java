package com.umbrella.util.crypto;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by xudazhou on 2015/10/22.
 */
public class DESUtil2 {

    private static final String DES = "DES";

    public static String encrypt(String text, String key, String charset) throws Exception {
        String res = null;
//        byte[] keyBase64 = Base64.decodeBase64(key.getBytes(charset));
//        DESKeySpec desKeySpec = new DESKeySpec(keyBase64);
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(charset)); // 取前8个字节
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);

        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] ciphertextByte = cipher.doFinal(text.getBytes(charset));
        res = new String(Base64.encodeBase64(ciphertextByte), charset);
        return res;
    }

    public static String encrypt2(String text, String key, String charset) throws Exception {
        String res = null;
        byte[] bytes = key.getBytes(charset);
        byte[] bytes2 = new byte[8];
        for(int i=0; i<bytes.length && i<bytes2.length; i++) {
            bytes2[i] = bytes[i];
        }
        Key secretKey = new SecretKeySpec(bytes2, DES);

        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] ciphertextByte = cipher.doFinal(text.getBytes(charset));
        res = new String(Base64.encodeBase64(ciphertextByte), charset);
        return res;
    }
}
