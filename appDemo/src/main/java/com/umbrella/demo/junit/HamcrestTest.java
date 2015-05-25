package com.umbrella.demo.junit;

import com.umbrella.vo.Student;
import com.umbrella.vo.User;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 大洲 on 14-12-16.
 */
public class HamcrestTest {
    @Test
    public void testHamcrest() {
        int i = 1>2?1:2;
        Assert.assertThat("", i, CoreMatchers.equalTo(2));
    }

    /**
     * 通过
     */
    @Test
    public void testHamcrest2() {
        List<String> list1 = new ArrayList<String>() {
            {
                add("a");
                add("b");
            }
        };
        Assert.assertThat("", list1, Matchers.hasItem("a"));
    }

    /**
     * 通过
     */
    @Test
    public void testHamcrest3() {
        User u1 = new User();
        u1.setId(2);
        Assert.assertThat(u1, Matchers.hasProperty("id"));
    }

    /**
     * 异常
     */
    @Test
    public void testHamcrest4() {
        User u1 = new User();
        u1.setId(2);
        Assert.assertThat(u1, Matchers.hasProperty("id"));
        Assert.assertThat(u1, HasPropertyWithValue.hasProperty("id", IsEqual.equalTo(2L)));
    }

    @Test
    public void testHamcrest5() {
        Student s1 = new Student();
        s1.setId(1);
        s1.setName("Alphonse");
        Assert.assertThat("",
                s1,
                Matchers.allOf(
                        Matchers.hasProperty("id", Matchers.equalTo(1L)),
                        Matchers.hasProperty("name", Matchers.equalTo("Alphonse"))
                )
        );
    }
}
