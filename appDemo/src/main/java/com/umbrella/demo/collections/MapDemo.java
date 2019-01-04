package com.umbrella.demo.collections;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xudazhou on 2017/2/8.
 */
public class MapDemo {

    /**
     * get 不存在的 key
     */
    @Test
    public void test1() {
        Map<String, Object> map1 = new HashMap<>();
        String str1 = (String) map1.get("k1");
        System.out.println(str1); // null
    }

    @Test
    public void test2() {
        Map<String, Double> m1 = new HashMap<>();
        m1.put("a", 1.3);
        m1.put("哈哈", 0.44);
        System.out.println(m1.containsKey("a")); // true
        System.out.println(m1.containsKey("哈哈")); // true
        System.out.println(null != m1.get("哈哈")); // true
    }

    /**
     * Map 的key 是怎么判断的
     */
    @Test
    public void test3() {
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        System.out.println(i1 == i2); // false
        Map<Integer, Integer> map1 = new HashMap<>();
        map1.put(i1, i1);
        System.out.println(map1.containsKey(i2)); // true
    }
}
