package com.umbrella.grammar.reflect;

import com.umbrella.vo.Passenger;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by 大洲 on 15-4-23.
 */
public class ReflectTest {
    @Test
    public void test1() {
        try {
            Field tickNo = Passenger.class.getDeclaredField("ticketNo");
            tickNo.setAccessible(true);
            Class clazz1 = tickNo.getType();
            System.out.println(clazz1.getName());

            System.out.println(clazz1.isAssignableFrom(long.class)); // true
            System.out.println(clazz1.isAssignableFrom(Long.class)); // false

            Type type1 = tickNo.getGenericType();
            System.out.println(type1.toString());

            // 可以获取父类的 public 属性
            Field birthDay = Passenger.class.getField("birthDay");
            tickNo.setAccessible(true);
            Class clazz2 = birthDay.getType();
            System.out.println(clazz2.getName()); // java.util.Date

            System.out.println(clazz2.isAssignableFrom(Date.class));

            Type type2 = birthDay.getGenericType();
            System.out.println(type2.toString()); // class java.util.Date

            /**
             * java.lang.NoSuchFieldException: BirthDay
             * 区分大小写
              */
            Field birthDay2 = Passenger.class.getField("BirthDay");

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
