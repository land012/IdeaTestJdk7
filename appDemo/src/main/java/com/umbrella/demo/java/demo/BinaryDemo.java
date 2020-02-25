package com.umbrella.grammar.demo;

import org.junit.Test;

/**
 * Created by 大洲 on 15-6-30.
 */
public class BinaryDemo {
    @Test
    public void test1() {
        int i = 5;
        System.out.println(Integer.toBinaryString(i)); // 101
        // 减号的优先级高于>>>
        System.out.println(i>>>(2-1)); // 二进制10
        System.out.println(i>>>2-1); // 二进制10
        System.out.println((i>>>2)-1); // 0
        System.out.println(Math.pow(2, 13));
    }

    @Test
    public void test2() {
        for(int i=1; i<10000; i++) {
            if(testNu1(i)) {
                System.out.println(i); // 8193
                break;
            }
        }
    }

    @Test
    public void test3() {
        int i=8193;
        while (true) {
            if(!testNu1(i)) {
                System.out.println(i); // 16385
                System.out.println(Integer.toBinaryString(i));
                break;
            }
            i++;
        }
    }

    /**
     * 如果 i=2^13 + 1 时，则返回true
     * @param i
     * @return
     */
    private Boolean testNu1(int i) {
        if((i-1>>>13)%2 == 1) return true;
        return false;
    }
}
