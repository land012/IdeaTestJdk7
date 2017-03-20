package com.umbrella.demo.java.util;

import com.umbrella.vo.People;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 大洲 on 15-1-23.
 */
public class ListDemo {
    /**
     * 验证 contains()方法
     */
    @Test
    public void test1() {
        List<String> list1 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        System.out.println(list1.contains("a")); // true
    }

    /**
     * 拿到的下标超界时，会抛异常
     * java.lang.IndexOutOfBoundsException
     */
    @Test
    public void test2() {
        List<String> list1 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        System.out.println(list1.get(2));
    }

    @Test
    public void test3() {
        List<String> list1 = new ArrayList<String>();
        list1.addAll(null); // java.lang.NullPointerException
    }

    /**
     * 遍历时修改 抛出异常
     * java.util.ConcurrentModificationException
     */
    @Test
    public void test4() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        Iterator<String> it1 = list1.iterator();
        while (it1.hasNext()) {
            String str1 = it1.next();
            if("b".equals(str1)) {
//                list1.remove(str1);
                list1.add("bb");
            }
        }
    }

    /**
     * CopyOnWriteArrayList
     * 不会抛异常
     */
    @Test
    public void test5() {
        List<String> list1 = new CopyOnWriteArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        // 只打印4个元素
//        Iterator<String> it1 = list1.iterator();
//        while (it1.hasNext()) {
//            String str1 = it1.next();
//            if("b".equals(str1)) {
//                list1.add("bb");
//            }
//            System.out.println(String.format("%s-%s-%s", list1.hashCode(), list1.size(), str1));
//        }

        // 会打印5个元素
        for(int i=0; i<list1.size(); i++) {
            String str1 = list1.get(i);
            if("b".equals(str1)) {
                list1.add("bb");
            }
            System.out.println(String.format("%s-%s-%s", list1.hashCode(), list1.size(), str1));
        }
    }

    /**
     * subList
     */
    @Test
    public void test6() {
        List<String> list1 = new CopyOnWriteArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        List<String> list2 = list1.subList(1, 2);
        System.out.println(list1); // [a, b, c, d]
        System.out.println(list2); // [b]
    }

    /**
     * LinkedList 本身就是双向队列
     */
    @Test
    public void test7() {
        LinkedList<String> list1 = new LinkedList<>();
        list1.add("a");
        list1.addFirst("b");
        list1.addLast("c");
        list1.add(2, "d");
        System.out.println(list1); // [b, a, d, c]
    }

    /**
     * 交集
     * 当有重复值时，交集也会保存两条
     */
    @Test
    public void test8RetainAll() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        list2.add(4);
        System.out.println(list1.retainAll(list2)); // true
        System.out.println(list1); // [2, 2, 3]
    }

    /**
     * 交集
     * 这种情况下，当有重复值时，只保存一条
     */
    @Test
    public void test8RetainAll1_1() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        list2.add(4);
        System.out.println(list2.retainAll(list1)); // true
        System.out.println(list2); // [2, 3]
    }

    /**
     * 交集
     * 没有交集
     */
    @Test
    public void test8RetainAll2() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(5);
        list2.add(4);
        System.out.println(list1.retainAll(list2)); // true
        System.out.println(list1.size()); // 0
        System.out.println(list1); // []
    }

    /**
     * 交集
     * 当前list是目标list的子集
     */
    @Test
    public void test8RetainAll3() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        System.out.println(list1.retainAll(list2)); // false
        System.out.println(list1); // [1, 2, 3]
    }

    /**
     * 并集
     * 不会过滤掉重复的元素
     */
    @Test
    public void testMerge() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        list2.add(4);
        System.out.println(list1.addAll(list2)); // true
        System.out.println(list1); // [1, 2, 3, 2, 3, 4]
    }

    /**
     * 差集
     * 当有重复时，两条都会删除
     */
    @Test
    public void testDifferenceSet() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(2);
        list1.add(3);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        list2.add(4);
        System.out.println(list1.removeAll(list2)); // true
        System.out.println(list1); // [1]

        // 另外一个集合是 empty
        List<Integer> list3 = new ArrayList<>();
        System.out.println(list2.removeAll(list3)); // false
        System.out.println(list2); // [2, 3, 4]
    }

    /**
     * toArray()
     */
    @Test
    public void test9() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("a");
        String[] strArr1 = new String[list1.size()];
        list1.toArray(strArr1);
        System.out.println(Arrays.toString(strArr1)); // [a, b, a]

        String[] strArr2 = new String[0];
        String[] strArr3 = list1.toArray(strArr2);
        System.out.println(Arrays.toString(strArr2)); // []
        System.out.println(Arrays.toString(strArr3)); // [a, b, a]
    }

    /**
     * 浅拷贝
     * 同时修改了 list2 的内容
     */
    @Test
    public void testListCopy() {
        People u1 = new People();
        u1.setId(1);
        u1.setName("tom");
        List<People> list1 = new ArrayList<>();
        list1.add(u1);

        List<People> list2 = new ArrayList<>();
        list2.addAll(list1);
        People u11 = list1.get(0);
        u11.setName("jim");
        System.out.println(list1); // [People[id=1,name=jim]]
        System.out.println(list2); // [People[id=1,name=jim]]
    }

    /**
     * 深拷贝
     */
    @Test
    public void testListCopy1_1() throws Exception {
        People u1 = new People();
        u1.setId(1);
        u1.setName("tom");
        List<People> list1 = new ArrayList<>();
        list1.add(u1);

        List<People> list2 = new ArrayList<>();
        list2.add(u1.clone());

        People u11 = list1.get(0);
        u11.setName("jim");

        System.out.println(list1); // [People[id=1,name=jim]]
        System.out.println(list2); // [People[id=1,name=tom]]
    }

    /**
     * 不会影响 List 的值
     */
    @Test
    public void testListCopy2() {
        String str1 = "a";
        List<String> list1 = new ArrayList<>();
        list1.add(str1);
        str1 = "b";
        System.out.println(list1); // [a]
    }

    /**
     * list 本身拷贝
     */
    @Test
    public void testListCopy3() {
        List<People> list1 = new ArrayList<>();

        People p1 = new People();
        p1.setId(1);
        p1.setName("tom");
        People p2 = new People();
        p2.setId(2);
        p2.setName("jim");

        list1.add(p1);
        list1.add(p2);

        List<People> list2 = new ArrayList<>();
        list2.addAll(list1);

        list1.remove(0);

        System.out.println(list1); // [People[id=2,name=jim]]
        System.out.println(list2); // [People[id=1,name=tom], People[id=2,name=jim]]
    }

    /**
     * list to array[]
     */
    @Test
    public void test10_toArray() {
        List<User> userList = new ArrayList<>();
        User u1 = new User();
        u1.setId(1);
        User u2 = new User();
        u2.setId(2);
        userList.add(u1);
        userList.add(u2);

        User[] users1 = new User[]{};
        User[] users2 = userList.toArray(users1);
        System.out.println(Arrays.toString(users1)); // []
        System.out.println(Arrays.toString(users2)); // [ListDemo.User[Id=1], ListDemo.User[Id=2]]
    }

    static class User {
        private int Id;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }
}
