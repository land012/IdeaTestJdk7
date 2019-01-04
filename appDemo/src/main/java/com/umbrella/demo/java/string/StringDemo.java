package com.umbrella.demo.java.string;

import com.umbrella.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by 大洲 on 15-4-20.
 */
public class StringDemo {
    /**
     * contains
     */
    @Test
    public void test1() {
        System.out.println("abc".contains("a")); // true
        System.out.println("abc".contains("A")); // false
        System.out.println(StringUtils.containsIgnoreCase("abc", "a")); // true
        System.out.println(StringUtils.containsIgnoreCase("abc", "A")); // true
    }

    /**
     * 占位符
     */
    @Test
    public void test2() {
        System.out.println(String.format("Hello %s", "Elric")); // Hello Elric
        System.out.println(String.format("Hello {1}", "Orochimaru")); // Hello {1}
        System.out.println(String.format("%1$s is %1$s", "Mikasa")); // Mikasa is Mikasa
        User u1 = null;
        System.out.println(String.format("user1=%s", u1));
    }

    /**
     * 占位符
     */
    @Test
    public void test3() {
        System.out.println(String.format("%3d", 1)); // 左补空格
        System.out.println(String.format("%3d", 1234));
        System.out.println(String.format("%-3d", 1)); // 左对齐， 不足右补空格
        System.out.println(String.format("%-3d", 1234));
        System.out.println(String.format("%+d", 12)); // +12 显示正负号
        System.out.println(String.format("%+d", -12)); // -12
        System.out.println(String.format("%05d", 123456)); // 123456
        System.out.println(String.format("%05d", 1234)); // 01234
        // 负数的处理
        System.out.println(String.format("%05d", -1234)); // -1234
        System.out.println(String.format("%010d", 1234)); // 00000 01234
        /*
         * java.util.IllegalFormatConversionException: d != java.lang.String
         */
//        System.out.println(String.format("%010d", "1234"));
        System.out.println(String.format("%010d", Integer.parseInt("1234"))); // 00000 01234
    }

    /**
     * 编码问题
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        String str1 = "亲亲袋鼠-VINCI";
        System.out.println("str1=" + str1);
        System.out.println("str1.length()=" + str1.length()); // 10 如果文件是gbk编码，这个长度返回 13！！！！？？？
        for(int i=0; i<str1.length(); i++) {
            char c1 = str1.charAt(i);
            System.out.print(c1);
//            System.out.print(Character.to);
            System.out.println();
        }
        System.out.println("str1.getBytes(\"utf-8\").length=" + str1.getBytes("utf-8").length); // 18 中文三字节 英文一字节
        System.out.println("str1.getBytes(\"gbk\").length=" + str1.getBytes("gbk").length);   // 14 中文两字节 英文一字节
    }

    @Test
    public void test4_2() throws Exception {
        String str1 = "哈";
        System.out.println(str1.getBytes("GBK").length); // 2
        System.out.println(str1.getBytes("UTF-8").length); // 3
    }

    /**
     * append 对 null不会抛异常
     */
    @Test
    public void test5() {
        String str1 = null;
        StringBuilder str = new StringBuilder();
        str.append(str1);
        str.append("a");
        System.out.println(str); // nulla
    }

    @Test
    public void test6Concat() {
        System.out.println("hel".concat("lo")); // hello
    }

    /**
     * NullPointerException
     */
    @Test
    public void testValueof() {
        System.out.println(String.valueOf(null));
    }

    @Test
    public void testEqual() {
        System.out.println("s".equals(null)); // false
        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");
        System.out.println(str1 == str2); // true
        System.out.println(str1 == str3); // false
    }

    @Test
    public void testCompare() {
        System.out.println("".compareTo("a")); // -1
        System.out.println("A".compareTo("a")); // -32
        System.out.println("A".compareTo("B")); // -1
        System.out.println("A".compareTo("C")); // -2
        System.out.println("A".compareToIgnoreCase("a")); // 0
        System.out.println("我".compareTo("你")); // 4785
    }

    /**
     * substring
     * @throws Exception
     */
    @Test
    public void test6_0() throws Exception {
        String str1 = "abcd";
        System.out.println(str1.substring(0, 2)); // ab
        System.out.println(str1); // abcd
        System.out.println(str1.substring(0, 0)); // 空串
    }

    @Test
    public void test6() throws Exception {
        String str1 = "news_1234567";
        int index = str1.lastIndexOf("_");
        System.out.println(index);
        System.out.println(str1.substring(index + 1));
        System.out.println(str1.substring(0, index));
    }

    @Test
    public void test7() {
        String str1 = "abc d ghi";
        System.out.println(str1.indexOf(" ")); // 3
        System.out.println(str1.indexOf(" ", 4)); // 5
        System.out.println(str1.indexOf(" ", 5)); // 5
        System.out.println(str1.indexOf(" ", 6)); // -1
    }

    /**
     * split
     */
    @Test
    public void test8() {
        String str1 = "a\tb c d e";
        String[] arr = str1.split("\\s", 3);
        System.out.println(arr.length); // 3
        for(String s1 : arr) {
            System.out.println(s1);
        }
    }

    @Test
    public void test9() {
        String logstr = "xxxxxxx copy_sim_doc";
        int beginInex = logstr.indexOf("copy_sim_doc");
        int leftQ = beginInex + 14;
        System.out.println(leftQ); // 22
        System.out.println(logstr.length()); // 20
        int rightQ = logstr.indexOf("\"", leftQ + 1); // 不会报下标越界...
        System.out.println(rightQ); // -1

        System.out.println(logstr.indexOf("")); // 0
        System.out.println(logstr.indexOf("", 1)); // 1
    }

    @Test
    public void test10_indexof() {
        String str1 = "abcdefg";
        System.out.println(str1.indexOf(2));
        System.out.println(str1.indexOf(97)); // 0
    }

    @Test
    public void test_replace() {
        String str1 = "abacad";
        String str2 = str1.replace("a", "x");
        System.out.println(str2);
    }

    /**
     * 内存地址
     */
    @Test
    public void test_mem_site() {
        String str1 = "abc";
        String str2 = "abc";
        StringBuffer sb1 = new StringBuffer(str1);
        StringBuffer sb2 = new StringBuffer(str1);
        System.out.println(System.identityHashCode(str1)); // 128526626
        System.out.println(System.identityHashCode(str2)); // 128526626
        System.out.println(System.identityHashCode(sb1));  // 1911728085
        System.out.println(System.identityHashCode(sb2));  // 754666084
    }
}
