package cf.umbrella.lzo;

import org.anarres.lzo.LzoAlgorithm;
import org.anarres.lzo.LzoCompressor;
import org.anarres.lzo.LzoDecompressor;
import org.anarres.lzo.LzoDecompressor1x;
import org.anarres.lzo.LzoInputStream;
import org.anarres.lzo.LzoLibrary;
import org.anarres.lzo.LzoOutputStream;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

/**
 * create by xudazhou 2018/3/1
 */
public class LzoDemo {

    private static Logger log = LoggerFactory.getLogger(LzoDemo.class);

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

    /**
     * 需要3g内存 且 workDir 是 D:\_idea2017\TestApp\appDemo\target\classes
     */
    @Test
    public void test2() {
        File lzostrfile = new File("lzostr.out");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(lzostrfile), "utf-8"))) {
            String line = br.readLine();

            byte[] base64Bytes = Base64.decodeBase64(line);

            log.info("base64Bytes.length={}", base64Bytes.length); // 383131

            ByteBuffer bb1 = ByteBuffer.allocate(8 + base64Bytes.length);
            bb1.putInt(1853632);
            bb1.putInt(base64Bytes.length);
            bb1.put(base64Bytes);

            LzoDecompressor lzoDecompressor = LzoLibrary.getInstance().newDecompressor(LzoAlgorithm.LZO1X, null);

            ByteArrayInputStream bais = new ByteArrayInputStream(bb1.array());
            LzoInputStream lis = new LzoInputStream(bais, lzoDecompressor);
            try (BufferedReader br2 = new BufferedReader(new InputStreamReader(lis, "utf-8"))) {
                System.out.println(br2.readLine()); //
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        File lzostrfile = new File("lzostr.out");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(lzostrfile), "utf-8"))) {
            String line = br.readLine();

            byte[] base64Bytes = Base64.decodeBase64(line);

            log.info("base64Bytes.length={}", base64Bytes.length); // 383131

//            ByteBuffer bb1 = ByteBuffer.allocate(8 + base64Bytes.length);
//            bb1.putInt(1853632);
//            bb1.putInt(base64Bytes.length);
//            bb1.put(base64Bytes);

            LzoDecompressor lzoDecompressor = new LzoDecompressor1x();

            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            LzoInputStream lis = new LzoInputStream(bais, lzoDecompressor);
            try (BufferedReader br2 = new BufferedReader(new InputStreamReader(lis, "utf-8"))) {
                System.out.println(br2.readLine()); //
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
