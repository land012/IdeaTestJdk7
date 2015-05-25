package com.umbrella.demo.junit;

import com.umbrella.vo.Student;
import com.umbrella.vo.User;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 大洲 on 14-12-12.
 */
public class JUnitDemo {
    @Test
    public void test1() {
        BigDecimal bd1 = new BigDecimal(12.0);
        Assert.assertEquals(12, bd1);
    }

    @Test
    public void test2() {
        BigDecimal bd1 = new BigDecimal(12.0);
        Assert.assertEquals(12, bd1.doubleValue());
    }

    @Test
    public void test3() {
        BigDecimal bd1 = new BigDecimal(12.0);
        Assert.assertEquals(12.0, bd1.doubleValue());
    }

    /**
     * 通过
     * 误差范围内允许通过
     */
    @Test
    public void test4() {
        Assert.assertEquals(1.2, 1.5, 0.5);
    }

    /**
     * 通不过
    * 误差范围之外，不允许通过
    */
    @Test
    public void test5() {
        Assert.assertEquals(1.2, 1.5, 0.2);
    }

    /**
     * 通过
     */
    @Test
    public void testCoreMatchers() throws ParseException {
        Assert.assertThat("",
                new SimpleDateFormat("yyyy-MM-dd").parse("2014-12-16"),
                CoreMatchers.equalTo(new SimpleDateFormat("yyyy-MM-dd").parse("2014-12-16")));
    }

    /**
     * 通不过
     * 重写了 hashCode() 和 toString()
     */
    @Test
    public void testCoreMatchers2() {
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("Alphonse");
        User u2 = new User();
        u2.setId(1);
        u2.setUserName("Alphonse");
        Assert.assertThat("", u1, CoreMatchers.equalTo(u2));
    }

}
