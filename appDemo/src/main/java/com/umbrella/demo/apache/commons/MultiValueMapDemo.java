package com.umbrella.demo.apache.commons;

import org.apache.commons.collections.map.MultiValueMap;
import org.junit.Test;

/**
 * Created by xudazhou on 2016/5/21.
 */
public class MultiValueMapDemo {
    @Test
    public void test1() {
        MultiValueMap m1 = new MultiValueMap();
        m1.put("k1", "v1_1");
        m1.put("k1", "v1_2");
        m1.put("k1", "v1_3");
        System.out.println(m1.get("k1")); // [v1_1, v1_2, v1_3]
    }
}
