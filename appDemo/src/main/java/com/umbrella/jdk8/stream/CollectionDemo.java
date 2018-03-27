package com.umbrella.jdk8.stream;

import com.umbrella.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xudazhou on 2016/12/21.
 */
public class CollectionDemo {
    /**
     * 遍历
     */
    @Test
    public void test1() {
        List<User> userList = new ArrayList<>();
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("Mikasa");
        u1.setGender(0);

        User u2 = new User();
        u2.setId(2);
        u2.setUserName("Hector");
        u2.setGender(1);

        userList.add(u1);
        userList.add(u2);

        List<Long> ids = userList
                .stream()
                .filter(e -> e.getGender() == 0)
                .map(User::getId)
                .collect(Collectors.toList());
        System.out.println(ids); // [1]

        List<Long> ids2 = userList
                .stream()
                .filter(e -> e.getGender() == 3)
                .map(User::getId)
                .collect(Collectors.toList());
        System.out.println(ids2 == null); // false
        System.out.println(ids2.size()); // 0
    }

    /**
     * trim List 中的 String 并转成一个新的 List
     */
    @Test
    public void test1_1() {
        List<String> list1 = new ArrayList<>();
        list1.add(" a");
        list1.add(" ");
        list1.add("b ");
        list1.add(" a ");

        List<String> list2 = list1
                .stream()
                .map(str -> StringUtils.trimToEmpty(str))
                .filter(str -> StringUtils.isNoneEmpty(str))
                .collect(Collectors.toList());

        List<String> list3 = list1
                .stream()
                .map(str -> StringUtils.trimToEmpty(str))
                .filter(str -> StringUtils.isNoneEmpty(str))
                .distinct() // 去重
                .collect(Collectors.toList());

        Set<String> set2 = list1
                .stream()
                .map(str -> StringUtils.trimToEmpty(str))
                .filter(str -> StringUtils.isNoneEmpty(str))
                .collect(Collectors.toSet());

        System.out.println(list1); // [ a,  , b ,  a ]
        System.out.println(list2); // [a, b, a]
        System.out.println(list3); // [a, b]
        System.out.println(set2);  // [a, b]
    }

    /**
     * 遍历
     * stream.forEach
     */
    @Test
    public void test1_2() {
        List<User> userList = new ArrayList<>();
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("Mikasa");

        User u2 = new User();
        u2.setId(2);
        u2.setUserName("Hector");

        userList.add(u1);
        userList.add(u2);

        userList.stream()
                .forEach(user -> System.out.println(user));
    }

    /**
     * 遍历
     * forEach
     */
    @Test
    public void test1_3() {
        List<String> list1 = new ArrayList<>();
        list1.add("c");
        list1.add("a");
        list1.add("d");

        list1.forEach(x -> System.out.println(x));

        StringBuilder res = new StringBuilder();
        list1.stream()
                .filter(x -> !"a".equals(x))
                .forEach(x -> res.append(x).append("|"));
        System.out.println(res); // c|d|

        StringBuilder res2 = new StringBuilder();
        list1.stream()
                .filter(x -> !"a".equals(x))
                .map(x -> res2.append(x).append("|"));
        System.out.println("res2=" + res2); // res2=
    }

    /**
     * 匿名类
     * 排序
     */
    @Test
    public void test2() {
        List<String> list1 = Arrays.asList("b", "B", "d", "A", "a", "c");
        Collections.sort(list1, (a, b) -> a.compareTo(b));
        System.out.println(list1); // [A, B, a, b, c, d]
    }

    /**
     * 匿名类
     */
    @Test
    public void test2_2() {
        List<String> list1 = Arrays.asList("b", "d", "a", "c");
        Collections.sort(list1, (a, b) ->  b.compareTo(a));
        System.out.println(list1); // [d, c, b, a]
    }


}
