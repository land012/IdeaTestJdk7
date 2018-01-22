package com.umbrella.demo.apache.commons.collections;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * create by xudazhou 2017/12/30
 */
public class CollectionUtilsTest {

    @Test
    public void test1() {
        Map<String, Integer> m1 = null;
        Map<String, Integer> m2 = new HashMap<>();
        Map<String, Integer> m3 = new HashMap<>();
        m3.put(null, null);
        System.out.println(MapUtils.isEmpty(m1)); // true
        System.out.println(MapUtils.isEmpty(m2)); // true
        System.out.println(MapUtils.isEmpty(m3)); // false
    }
}
