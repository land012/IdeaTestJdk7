package com.umbrella.demo.junit;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Dates;
import org.assertj.core.util.Maps;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 大洲 on 15-1-10.
 */
public class AssertJ {
    @Test
    public void test1() {
        String str1 = null;
        Assertions.assertThat(str1).isNull();
        // 不通过 Expecting actual not to be null
        Assertions.assertThat(str1).isEmpty();
    }

    @Test
    public void test2() {
        String str1 = "";
        Assertions.assertThat(str1).isEmpty();
    }

    @Test
    public void test3() {
        String str1 = "Hello";
        Assertions.assertThat(str1).isEqualTo("Hello")
                .isEqualToIgnoringCase("hello")
                .startsWith("Hel")
                .endsWith("llo")
                .hasSize(5)
                .contains("ll")
                .doesNotContain("world")
                .containsOnlyOnce("el"); // 仅出现一次
    }

    @Test
    public void test4() {
        String str1 = "Hello";
        Assertions.assertThat(str1)
                .matches(".+ll.");
    }

    /**
     * ===================== 数字 ==============================
     */

    @Test
    public void test5() {
        Assertions.assertThat(5)
                .isEqualTo(5)
                .isNotEqualTo(6)
                .isPositive()
                .isNotNegative();
        Assertions.assertThat(0).isZero().isGreaterThan(-1);
    }

    /**
     * ===================== 日期 ==============================
     */

    @Test
    public void test6() {
        Assertions.assertThat(Dates.parse("2015-01-01"))
                .isEqualTo("2015-01-01")
                .isBefore("2015-04-03")
                .isAfter("2014-12-31")
                .isBeforeYear(2016)
                .isAfterYear(2014)
                .isBetween("2014-12-31", "2015-01-02");
    }

    @Test
    public void test7() {
        Assertions.assertThat(Dates.parse("2015-01-01"))
                .isBetween("2015-01-01", "2015-01-01", true, true); // 包含起始日期
    }

    @Test
    public void test8() {
        Date d1 = Dates.parseDatetimeWithMs("2015-01-01T12:00:00.99");
        Date d2 = Dates.parseDatetimeWithMs("2015-01-01T12:00:00.98");
        Assertions.assertThat(d1).isCloseTo(d2, 1); // 相差毫秒数
        Assertions.assertThat(d2).isCloseTo(d1, 1);

        Assertions.assertThat(d1).isEqualToIgnoringMillis(d2);
        Assertions.assertThat(d1).isInSameSecondAs(d2);
    }

    /**
     * ===================== 列表 ==============================
     */

    @Test
    public void test9() {
        Assertions.assertThat(new ArrayList<String>()).isEmpty();
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        Assertions.assertThat(list1)
                .startsWith(1)
                .contains(2, Assertions.atIndex(1))
                .isSorted();
    }

    @Test
    public void test10() {
        Map<String, Object> map1 = Maps.newHashMap();
        map1.put("A", 1);
        map1.put("B", 2);
        Assertions.assertThat(map1)
                .contains(Assertions.entry("A", 1));
    }
}
