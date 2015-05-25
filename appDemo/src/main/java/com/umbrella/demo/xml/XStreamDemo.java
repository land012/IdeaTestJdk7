package com.umbrella.demo.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.NullConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.umbrella.demo.xml.converter.DateConverter;
import com.umbrella.vo.Platoon;
import com.umbrella.vo.Soldier;
import com.umbrella.vo.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by 大洲 on 15-4-22.
 */
public class XStreamDemo {
    /**
     * Object to XML
     * 不显示 值为 null 的属性
     * 别名
     */
    @Test
    public void test1() {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("user", User.class);

        User u1 = new User();
        u1.setId(1);
        u1.setBirthDay(new Date()); // 转到的xml是0时区的时间
        String res = xStream.toXML(u1);
        System.out.println(res);
    }

    /**
     * List to XML
     * 不显示 值为 null 的属性
     */
    @Test
    public void test2() {
        XStream xStream = new XStream(new DomDriver());

        User u1 = new User();
        u1.setId(1);
        u1.setBirthDay(new Date()); // 转到的xml是0时区的时间

        User u2 = new User();
        u2.setId(2);
        u2.setBirthDay(new Date());

        List<User> list = new ArrayList<User>();
        list.add(u1);
        list.add(u2);

        xStream.alias("user", User.class);
        xStream.alias("users", List.class);

        String res = xStream.toXML(list);
        System.out.println(res);
    }

    /**
     * 日期处理
     */
    @Test
    public void test3() {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("user", User.class);
        xStream.registerConverter(new DateConverter(Locale.CHINA));

        User u1 = new User();
        u1.setId(1);
        u1.setBirthDay(new Date()); // 转到的xml是0时区的时间
        String res = xStream.toXML(u1);
        System.out.println(res);
    }

    /**
     * NullConverter ?????
     */
    @Test
    public void test41() {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("user", User.class);

        User u1 = null;
        String res = xStream.toXML(u1);
        System.out.println(res); // <null/>
    }

    /**
     * NullConverter ?????
     */
    @Test
    public void test42() {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("user", User.class);
        xStream.registerConverter(new NullConverter());

        User u1 = null;
        String res = xStream.toXML(u1);
        System.out.println(res); // <null/>
    }

    /**
     * 别名
     * 两级 List，两属性名的List，不会改变别名
     */
    @Test
    public void test5() {
        Soldier s1 = new Soldier();
        s1.setId(1);
        s1.setName("Oda Nobunaga");

        Soldier s2 = new Soldier();
        s2.setId(2);
        s2.setName("Uchiha Sasuke");

        Soldier s3 = new Soldier();
        s3.setId(3);
        s3.setName("Leah Dizon");

        Soldier s4 = new Soldier();
        s4.setId(4);
        s4.setName("Sanada Yukimura");

        Platoon p1 = new Platoon();
        p1.setId(1);
        List<Soldier> l1 = new ArrayList<Soldier>();
        l1.add(s1);
        l1.add(s2);
        p1.setSoldiers(l1);

        Platoon p2 = new Platoon();
        p2.setId(1);
        List<Soldier> l2 = new ArrayList<Soldier>();
        l2.add(s3);
        l2.add(s4);
        p2.setSoldiers(l2);

        List<Platoon> platoons = new ArrayList<Platoon>();
        platoons.add(p1);
        platoons.add(p2);

        XStream xStream = new XStream(new DomDriver());
        xStream.alias("soldier", Soldier.class);
        xStream.alias("platoon", Platoon.class);
        xStream.alias("platoons", List.class);
        System.out.println(xStream.toXML(platoons));
    }

    /**
     * 别名
     * 指定属性的别名
     */
    @Test
    public void test6() {
        Soldier s1 = new Soldier();
        s1.setId(1);
        s1.setName("Oda Nobunaga");

        Soldier s2 = new Soldier();
        s2.setId(2);
        s2.setName("Uchiha Sasuke");

        Soldier s3 = new Soldier();
        s3.setId(3);
        s3.setName("Leah Dizon");

        Soldier s4 = new Soldier();
        s4.setId(4);
        s4.setName("Sanada Yukimura");

        Platoon p1 = new Platoon();
        p1.setId(1);
        List<Soldier> l1 = new ArrayList<Soldier>();
        l1.add(s1);
        l1.add(s2);
        p1.setSoldiers(l1);

        Platoon p2 = new Platoon();
        p2.setId(1);
        List<Soldier> l2 = new ArrayList<Soldier>();
        l2.add(s3);
        l2.add(s4);
        p2.setSoldiers(l2);

        List<Platoon> platoons = new ArrayList<Platoon>();
        platoons.add(p1);
        platoons.add(p2);

        XStream xStream = new XStream(new DomDriver());
        xStream.alias("soldier", Soldier.class);
        xStream.alias("platoon", Platoon.class);
        xStream.alias("platoons", List.class);
        xStream.aliasAttribute(Platoon.class, "soldiers", "soldierList");
        System.out.println(xStream.toXML(platoons));
    }
}
