package com.umbrella.demo.qualifier;

import org.junit.Test;

/**
 * create by xudazhou 2019/1/4
 */
public class QualifierUtilsTest {

    @Test
    public void test1() {
        System.out.println(QualifierUtils.underToCamel("user_name_1om_cat"));
        System.out.println(QualifierUtils.underToCamel("user"));
    }
}
