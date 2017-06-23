package com.umbrella.demo.java.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by 大洲 on 15-7-5.
 */
public class RandomDemo {

    private static final Logger log = LoggerFactory.getLogger(RandomDemo.class);

    /**
     * 当指定seed时，每次执行，都会得到相同的随机数序列
     */
    @Test
    public void test1() {
        Random r1 = new Random(1);
        log.info(r1.nextInt(6) + ""); // 3
        log.info(r1.nextInt(6) + ""); // 4
        log.info(r1.nextInt(6) + ""); // 1
        Random r2 = new Random(1);
        log.info(new Integer(r2.nextInt(6)).toString()); // 3
        log.info(new Integer(r2.nextInt(6)).toString()); // 4
        log.info(new Integer(r2.nextInt(6)).toString()); // 1

        Random r3 = new Random(2);
        log.info(r3.nextInt(6) + ""); // 4
        log.info(r3.nextInt(6) + ""); // 0
        log.info(r3.nextInt(6) + ""); // 2
        log.info(r3.nextInt(1) + "");
    }

    /**
     * 同一个值，会有可能连续出现
     */
    @Test
    public void test2() {
        long step = 1;
        Random r = new Random();
        int current = r.nextInt(10); // 0-9
        int last = current;
        current = r.nextInt(10);
        log.info("step=" + step + ", current=" + current);
        while(last!=current) {
            step++;
            last = current;
            current = r.nextInt(10);
            log.info("step=" + step + ", current=" + current);
        }
        log.info("current=" + current);
    }

    @Test
    public void test3() {
        Random r1 = new Random();
        System.out.println(r1.nextInt(100));
        System.out.println(r1.nextInt(100));

        System.out.println("========================================");
        Random r2 = new Random();
        System.out.println(r2.nextInt(100));
        System.out.println(r2.nextInt(100));
    }
}
