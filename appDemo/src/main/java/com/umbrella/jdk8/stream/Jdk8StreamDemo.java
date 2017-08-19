package com.umbrella.jdk8.stream;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * Created by xudazhou on 2017/7/28.
 */
public class Jdk8StreamDemo {

    /**
     * findFirst
     * 只输出 a
     */
    @Test
    public void test1() {
        Stream.of("a", "b", "c")
                .findFirst()
                .map(s -> {
                    System.out.println(s);
                    return  s;
                });
    }
}
