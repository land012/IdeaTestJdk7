package com.umbrella.grammar;

import org.junit.Test;

/**
 * Created by xudazhou on 2017/1/13.
 */
public class NullDemo {
    @Test
    public void test1() {
        System.out.println("============================= begin =================================");
        Object obj1 = null;
        // 不会抛异常
        if (obj1 instanceof String) {
            System.out.println("obj1 is String");
        }

        String obj2 = null;
        if (obj2 instanceof String) {
            System.out.println("obj2 is String"); // 没有打印
        }
        if (obj2 instanceof Object) {
            System.out.println("obj2 is Object"); // 没有打印
        }
        System.out.println("============================= end =================================");
    }
}
