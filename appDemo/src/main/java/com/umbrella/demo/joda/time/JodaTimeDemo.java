package com.umbrella.demo.joda.time;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.junit.Test;

/**
 * Created by 大洲 on 15-1-14.
 */
public class JodaTimeDemo {
    /**
     * 加一个月
     */
    @Test
    public void test1() {
        DateTime dt = DateTime.now();
        System.out.println(dt);
        DateTime lastMonth = dt.minusMonths(1);
        System.out.println(lastMonth);

        DateTime dt1 = DateTime.parse("2014-12-31");
        System.out.println(dt1);
        DateTime lastMonth1 = dt1.minusMonths(1);
        System.out.println(lastMonth1);

        DateTime dt2 = DateTime.parse("2014-3-1");
        System.out.println(dt2);
        DateTime lastMonth2 = dt2.minusMonths(1);
        System.out.println(lastMonth2);
    }

    /**
     * 计算间隔时长
     */
    @Test
    public void test2() {
        Instant start = new Instant();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Interval(start, new Instant()).toDuration().getMillis());
    }
}
