package cf.umbrella.lzo;

import org.anarres.lzo.LzoAlgorithm;
import org.anarres.lzo.LzoCompressor;
import org.anarres.lzo.LzoDecompressor;
import org.anarres.lzo.LzoInputStream;
import org.anarres.lzo.LzoLibrary;
import org.anarres.lzo.LzoOutputStream;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;

/**
 * create by xudazhou 2018/3/1
 */
public class LzoDemo {

    @Test
    public void test1() throws Exception {
        String str1 = "abcdefg";
        LzoCompressor lzoCompressor = LzoLibrary.getInstance().newCompressor(LzoAlgorithm.LZO1X, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (LzoOutputStream los = new LzoOutputStream(baos, lzoCompressor, 256)) {
            los.write(str1.getBytes("utf-8"));
        }

        String base64Str = Base64.encodeBase64String(baos.toByteArray());
        System.out.println(base64Str); // AAAABwAAAAsYYWJjZGVmZxEAAA==

        byte[] base64Bytes = Base64.decodeBase64(base64Str);

        LzoDecompressor lzoDecompressor = LzoLibrary.getInstance().newDecompressor(LzoAlgorithm.LZO1X, null);

        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new LzoInputStream(bais, lzoDecompressor)))) {
            System.out.println(br.readLine()); // abcdefg
        }
    }
}
