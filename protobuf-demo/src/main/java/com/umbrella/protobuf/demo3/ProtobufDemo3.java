package com.umbrella.protobuf.demo3;

import com.google.protobuf.ByteString;
import org.junit.Test;

/**
 * Created by xudazhou on 2017/5/2.
 */
public class ProtobufDemo3 {

    /**
     * 旧的对象会保留新的对象的字段，并在序列化时，序列化新的对象的字段，而不会丢弃
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        PersonSampleNew.Person.Builder pNewB = PersonSampleNew.Person.newBuilder();
        pNewB.setId(2);
        pNewB.setName(ByteString.copyFrom("tom", "utf-8"));
        pNewB.setEmail(ByteString.copyFrom("tom@163.com", "utf-8"));

        PersonSampleNew.Person pNew1 = pNewB.build();
        System.out.println(pNew1.toString());

        System.out.println("======================= 1 =========================");

        PersonSampleOld.Person pOld1 = PersonSampleOld.Person.parseFrom(pNew1.toByteArray());
        System.out.println(pOld1.toString());

        System.out.println("======================= 2 =========================");

        PersonSampleNew.Person pNew2 = PersonSampleNew.Person.parseFrom(pOld1.toByteArray());
        System.out.println(pNew2.toString());
    }
}
