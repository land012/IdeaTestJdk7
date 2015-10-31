package com.umbrella.util.crypto;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;

/**
 * Created with IntelliJ IDEA.
 * User: changzilian
 * Date: 13-3-13
 * Time: 下午6:21
 * To change this template use File | Settings | File Templates.
 */
public class DESUtil {

    private static final String DES = "DES";

    Key key;

    private DESUtil() { }

    public DESUtil(String key) throws Exception {
        genKey(key); // 生成密匙
    }

    /**
     * 根据参数生成 KEY
     */
    private void genKey(String strKey) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(DES);
        byte[] bytes = strKey.getBytes();
//        byte[] bytes2 = new byte[8];
//        for(int i=0; i<bytes.length && i<bytes2.length; i++) {
//            bytes2[i] = bytes[i];
//        }
        SecureRandom secureRandom = new SecureRandom(bytes);
        keyGenerator.init(secureRandom);
        this.key = keyGenerator.generateKey();
    }

    /**
     * 加密 String 明文输入 ,String 密文输出
     */
    public String encryptStr(String plaintext, String charset) throws Exception {
        String ciphertext = "" ;
        BASE64Encoder base64en = new BASE64Encoder();
        byte[] byteMing = plaintext.getBytes(charset);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] byteMi = cipher.doFinal(byteMing);
        ciphertext = base64en.encode(byteMi);
        return ciphertext;
    }

    /**
     * 解密 以 String 密文输入 ,String 明文输出
     *
     * @param ciphertext
     * @return
     */
    public String decryptStr(String ciphertext, String charset) throws Exception{
        String plaintext = "" ;
        BASE64Decoder base64De = new BASE64Decoder();
        byte[] byteMi = base64De.decodeBuffer(ciphertext);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] byteMing = cipher.doFinal(byteMi);
        plaintext = new String(byteMing, charset);
        return plaintext;
    }

    /**
     * 文件 file 进行加密并保存目标文件 destFile 中
     * @param file 要加密的文件 如 c:/test/srcFile.txt
     * @param destFile 加密后存放的文件名 如 c:/ 加密后文件 .txt
     */
    public void encryptFile(String file, String destFile) throws Exception {
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        InputStream is = new FileInputStream(file);
        OutputStream out = new FileOutputStream(destFile);
        CipherInputStream cis = new CipherInputStream(is, cipher);
        byte[] buffer = new byte [1024];
        int r;
        while ((r = cis.read(buffer)) > 0) {
            out.write(buffer, 0, r);
        }
        cis.close();
        is.close();
        out.close();
    }

    /**
     * 文件采用 DES 算法解密文件
     * @param file 已加密的文件 如 c:/加密后文件 .txt *
     * @param dest 解密后存放的文件名 如 c:/test/解密后文件 .txt
     */
    public void decryptFile(String file, String dest) throws Exception {
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.DECRYPT_MODE, this.key);
        InputStream is = new FileInputStream(file);
        OutputStream out = new FileOutputStream(dest);
        CipherOutputStream cos = new CipherOutputStream(out, cipher);
        byte [] buffer = new byte [1024];
        int r;
        while ((r = is.read(buffer)) >= 0) {
            cos.write(buffer, 0, r);
        }
        cos.close();
        out.close();
        is.close();
    }

}
