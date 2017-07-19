package com.umbrella.protobuf.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.TextFormat;
import com.googlecode.protobuf.format.JsonFormat;
import com.googlecode.protobuf.format.JsonJacksonFormat;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xudazhou on 2017/3/2.
 */
public class PersonDemo {

    private static Logger log = LoggerFactory.getLogger(PersonDemo.class);

    /**
     * ProtoBuf demo
     * 验证序列化和反序列化
     * @throws Exception
     */
    @Test
    public void test1_demo() throws Exception {
        PersonSample.Person.Builder builder = PersonSample.Person.newBuilder();
        builder.setId(1);
        builder.setName(ByteString.copyFrom("小李".getBytes()));
        builder.setEmail(ByteString.copyFrom("xiaoli@163.com".getBytes()));

        PersonSample.Person p1 = builder.build();
        System.out.println("===================== 1 =======================");
        /*
         * id: 1
         * name: "\345\260\217\346\235\216"
         * email: "xiaoli@163.com"
         */
        System.out.println(p1.toString());
        System.out.println(p1.getName().toStringUtf8()); // 小李
        System.out.println("===================== 2 =======================");
        ByteString bsP1 = p1.toByteString();
        System.out.println(bsP1); // <ByteString@7ff2a664 size=26>
        System.out.println(bsP1.toStringUtf8()); // 小李xiaoli@163.com
        System.out.println("===================== 3 =======================");

        byte[] byteArr1 = p1.toByteArray();

        PersonSample.Person p2 = PersonSample.Person.parseFrom(byteArr1);
        /*
         * id: 1
         * name: "\345\260\217\346\235\216"
         * email: "xiaoli@163.com"
         */
        System.out.println(p2);
        System.out.println(p2.getName().toStringUtf8()); // 小李
    }

    /**
     * pb to json
     * json to pb
     * 中文有问题
     */
    @Test
    public void test2_pb2json() throws Exception {
        PersonSample.Person.Builder builder = PersonSample.Person.newBuilder();
        builder.setId(1);
        builder.setName(ByteString.copyFromUtf8("汤姆"));
        builder.setEmail(ByteString.copyFromUtf8("tom@baidu.com"));
        PersonSample.Person p1 = builder.build();

        com.umbrella.protobuf.util.JsonFormat jf = new com.umbrella.protobuf.util.JsonFormat();
        String json1 = jf.printToString(p1);
        System.out.println(json1); // {"id": 1,"name": "汤姆","email": "tom@baidu.com"}
        System.out.println(Arrays.toString(p1.getName().toByteArray())); // [-26, -79, -92, -27, -89, -122]
        System.out.println(StringEscapeUtils.unescapeJava(json1)); // {"id": 1,"name": "汤姆","email": "tom@baidu.com"}

        System.out.println("=============================================");

        JsonFormat jf2 = new JsonFormat();
        System.out.println(jf2.printToString(p1)); // {"id": 1,"name": "\uffe6\uffb1\uffa4\uffe5\uffa7\uff86","email": "tom@baidu.com"}

        // 反序列化后，彻底乱码
        PersonSample.Person.Builder b2 = PersonSample.Person.newBuilder();
        jf.merge(json1, ExtensionRegistry.getEmptyRegistry(), b2);
        PersonSample.Person p2 = b2.build();
        System.out.println(p2.getName().toStringUtf8()); // 汤姆
    }

    /**
     * pb to json
     * JsonJackson
     * 中文有问题
     */
    @Test
    public void test2_2_pb2json() throws Exception {
        PersonSample.Person.Builder builder = PersonSample.Person.newBuilder();
        builder.setId(1);
        builder.setName(ByteString.copyFromUtf8("汤姆"));
        builder.setEmail(ByteString.copyFromUtf8("tom@baidu.com"));
        PersonSample.Person p1 = builder.build();

        JsonJacksonFormat jjf = new JsonJacksonFormat();
        String json1 = jjf.printToString(p1);
        System.out.println(json1); // {"id":1,"name":"5rGk5aeG","email":"dG9tQGJhaWR1LmNvbQ=="}
    }

    /**
     * json to pb
     * json 不存在某个字段
     * @throws Exception
     */
    @Test
    public void test3_0_json2pb() throws Exception {
        String json1 = "{\"id\":1,\"name\":\"tom\"}";
        PersonSample.Person.Builder builder1 = PersonSample.Person.newBuilder();
        JsonFormat jf = new JsonFormat();
        jf.merge(json1, ExtensionRegistry.getEmptyRegistry(), builder1);
        PersonSample.Person p1 = builder1.build();
        System.out.println(p1.hasEmail()); // false
        System.out.println(p1.getEmail() == null); // false
        byte[] bytes = p1.getEmail().toByteArray();
        System.out.println(bytes == null); // false
        System.out.println(bytes.length); // 0 空的字节数组
        System.out.println(StringUtils.isEmpty(p1.getEmail().toStringUtf8())); // true
    }

    /**
     * json to pb
     * 中文乱码问题
     * @throws Exception
     */
    @Test
    public void test3_json2pb_chs() throws Exception {
        Person p1 = new Person();
        p1.setId(1);
        p1.setName("汤姆");
        p1.setEmail("tom@baidu.com");
        ObjectMapper om = new ObjectMapper();
        String json1 = om.writeValueAsString(p1);
        System.out.println(json1); // {"id":1,"name":"汤姆","email":"tom@baidu.com"}

        PersonSample.Person.Builder builder1 = PersonSample.Person.newBuilder();
        JsonFormat jf = new JsonFormat();
        ExtensionRegistry er1 = ExtensionRegistry.newInstance();
        jf.merge(json1, er1, builder1);
        PersonSample.Person p2 = builder1.build();
        System.out.println(p2.getName().toStringUtf8()); // d�
        System.out.println(new String(p2.getName().toByteArray(), "utf-8")); // d�
    }

    /**
     * json 转 pb
     * 中文乱码问题解决
     * @throws Exception
     */
    @Test
    public void test3_2_json2pb_chs() throws Exception {
        Person p1 = new Person();
        p1.setId(1);
        p1.setName("汤");
        p1.setEmail("tom@baidu.com");
        ObjectMapper om = new ObjectMapper();
        String json1 = om.writeValueAsString(p1);
        System.out.println(json1);

        PersonSample.Person.Builder builder1 = PersonSample.Person.newBuilder();
        com.umbrella.protobuf.util.JsonFormat jf = new com.umbrella.protobuf.util.JsonFormat();
        ExtensionRegistry er1 = ExtensionRegistry.newInstance();
        jf.merge(json1, er1, builder1);
        PersonSample.Person p2 = builder1.build();
        System.out.println(p2.getName().toStringUtf8()); // 汤姆
        System.out.println(TextFormat.escapeBytes(p2.getName())); // \346\261\244\345\247\206
    }

    /**
     * 从utf-8编码的文件中读取json
     * @throws Exception
     */
    @Test
    public void test4_json2pb() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("E:\\TDDOWNLOAD\\json.txt"));
        String json1 = br.readLine();
        br.close();
        System.out.println(json1); // {"id":1,"name":"汤姆","email":"tom@baidu.com"}
        PersonSample.Person.Builder builder1 = PersonSample.Person.newBuilder();
        JsonFormat jf = new JsonFormat();
        ExtensionRegistry er1 = ExtensionRegistry.newInstance();
        jf.merge(json1, er1, builder1);
        PersonSample.Person p2 = builder1.build();
        System.out.println(p2.getName().toStringUtf8()); // d�
    }

    /**
     * 单独修改一个字段
     */
    @Test
    public void test5() {
        PersonSample.Person.Builder b1 = PersonSample.Person.newBuilder();
        b1.setId(2);
        b1.setName(ByteString.copyFromUtf8("zhang"));
        b1.setEmail(ByteString.copyFromUtf8("zhang@baidu.com"));
        PersonSample.Person p1 = b1.build();
        PersonSample.Person.Builder b2 = p1.toBuilder();
        b2.setName(ByteString.copyFromUtf8("wang"));
        PersonSample.Person p2 = b2.build();
        System.out.println(p2.toString());
    }

    /**
     * 单独修改一个字段
     */
    @Test
    public void test5_2() {
        PersonSample.Person.Builder b1 = PersonSample.Person.newBuilder();
        b1.setId(2);
        b1.setName(ByteString.copyFromUtf8("zhang"));
        b1.setEmail(ByteString.copyFromUtf8("zhang@baidu.com"));
        PersonSample.Person p1 = b1.build();

        PersonSample.Person.Builder b2 = PersonSample.Person.newBuilder(p1);
        b2.setName(ByteString.copyFromUtf8("wang"));
        PersonSample.Person p2 = b2.build();
        System.out.println(p2.toString());
    }

    /**
     * 单独修改一个字段
     */
    @Test
    public void test6_reflect() {
        PersonSample.Person.Builder b1 = PersonSample.Person.newBuilder();
        b1.setId(2);
        b1.setName(ByteString.copyFromUtf8("zhang"));
        b1.setEmail(ByteString.copyFromUtf8("zhang@baidu.com"));
        b1.addHobbies("reading");
        b1.addHobbies("fishing");
        PersonSample.Person p1 = b1.build();

        Descriptors.Descriptor d1 = p1.getDescriptorForType();
        List<Descriptors.FieldDescriptor> fieldDescriptorList = d1.getFields();
        for (Descriptors.FieldDescriptor fd : fieldDescriptorList) {
//            log.info(fd.getFullName()); // Person.id
            log.info("{}|{}|{}", new Object[]{fd.getJavaType(), fd.getLiteJavaType(), fd.getJsonName()});
            Object o1 = p1.getField(fd);
            log.info(o1 + "");
        }
    }

    @Test
    public void test7_writefile() throws Exception {
        PersonSample.Person.Builder builder = PersonSample.Person.newBuilder();
        builder.setId(12);
        builder.setName(ByteString.copyFrom("asuka", "utf-8"));

        PersonSample.Person p1 = builder.build();

        FileOutputStream fos = new FileOutputStream("E:\\TDDOWNLOAD\\file3.dat");
        fos.write(p1.toByteArray());
        fos.flush();
        fos.close();
    }

    @Test
    public void test7_readfile() throws IOException {
        FileInputStream fis = new FileInputStream("E:\\TDDOWNLOAD\\file2.dat");
//        int i1 = fis.read();
//        System.out.println(i1);

        byte[] buf = new byte[15];
        int len = fis.read(buf);
        System.out.println(len);

        for (int i=0; i < buf.length; i++) {
            System.out.print(buf[i] + "|");
        }
        System.out.println("=========== 1 ===========");

        ByteBuffer byteBuffer = ByteBuffer.allocate(len);
        byteBuffer.put(buf, 0, len);
        for (int i=0; i<byteBuffer.array().length; i++) {
            System.out.print(byteBuffer.get(i) + "|");
        }
        System.out.println("=========== 2 ===========");

        PersonSample.Person p1 = PersonSample.Person.parseFrom(byteBuffer.array());
        System.out.println(p1.toString());
    }

}
