package com.umbrella.util.crypto;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * Created by xudazhou on 2015/10/22.
 * 高阳的 CipherUtil
 */
public class DESPlus {

    private static final String DES = "DES";

    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;

    private String charset;

    /**
     * 指定密钥构造方法
     * @param strKey 指定的密钥
     * @throws Exception
     */
    public DESPlus(String strKey, String charset) throws Exception {
        this.charset = charset;

        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Key key = genKey(strKey.getBytes(this.charset));

        encryptCipher = Cipher.getInstance(DES);
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        decryptCipher = Cipher.getInstance(DES);
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }

    /**
     * 加密字符串
     * @param plaintext 需加密的字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public String encrypt(String plaintext) throws Exception {
        byte[] bytes = encryptCipher.doFinal(plaintext.getBytes(this.charset));
        return byteArr2HexStr(bytes);
    }

    /**
     * 将加密结果用 Base64 编码
     * @param plaintext
     * @return
     * @throws Exception
     */
    public String encrypt2(String plaintext) throws Exception {
        byte[] bytes = encryptCipher.doFinal(plaintext.getBytes(this.charset));
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 解密字符串
     * @param ciphertext 需解密的字符串
     * @return 解密后的字符串
     * @throws Exception
     */
    public String decrypt(String ciphertext) throws Exception {
        byte[] bytes = hexStr2ByteArr(ciphertext);
        return new String(decryptCipher.doFinal(bytes));
    }

    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程
     * @param arrB 需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     */
    public String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
     * 互为可逆的转换过程
     *
     * @param strIn 需要转换的字符串
     * @return 转换后的byte数组
     * @throws Exception 本方法不处理任何异常，所有异常全部抛出
     * @author LiGuoQing
     */
    public byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     *
     * @param arrBTmp 构成该字符串的字节数组
     * @return 生成的密钥
     * @throws Exception
     */
    private Key genKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];
        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        Key key = new SecretKeySpec(arrB, DES);
        return key;
    }
}
