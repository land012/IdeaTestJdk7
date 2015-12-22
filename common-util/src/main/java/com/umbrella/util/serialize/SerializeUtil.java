package com.umbrella.util.serialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具
 * Created by xudazhou on 2015/12/22.
 */
public class SerializeUtil {

    private static final Logger log = LoggerFactory.getLogger(SerializeUtil.class);

    public static <T> byte[] serialize(T t) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(t);
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    public static <T> T unserialize(byte[] bytes, Class<T> clazz) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T)ois.readObject();
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }
}
