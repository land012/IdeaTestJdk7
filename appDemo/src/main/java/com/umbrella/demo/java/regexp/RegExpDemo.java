package com.umbrella.demo.java.regexp;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 大洲 on 15-4-20.
 */
public class RegExpDemo {

    private static final Logger log = LoggerFactory.getLogger(RegExpDemo.class);

    // 数字，包含小数点 .
    private static final String NUMBER_REG = "^[\\d[\\.]?]+$";

    @Test
    public void test1() {
        System.out.println(Pattern.matches("a|b|c", "adef")); // false
        System.out.println(Pattern.compile("a|b|c").matcher("adef").matches()); // false
        System.out.println(Pattern.compile("a|b|c").matcher("adef").find()); // true
    }

    @Test
    public void test1_2() {
        System.out.println(Pattern.matches("^a.*", "adef")); // true
        System.out.println(Pattern.compile("^a.*").matcher("adef").matches()); // true
        System.out.println(Pattern.compile("^a.*").matcher("adef").find()); // true
    }

    @Test
    public void test1_3() {
        System.out.println(Pattern.matches("^a", "adef")); // false
        System.out.println(Pattern.compile("^a").matcher("adef").find()); // true
    }

    @Test
    public void test1_4() {
        System.out.println(Pattern.matches("a", "adef")); // false
        System.out.println(Pattern.compile("a").matcher("adef").find()); // true
        System.out.println(Pattern.compile("ba").matcher("adef").find()); // false
    }

    /**
     * group()
     */
    @Test
    public void test2() {
        Pattern pattern = Pattern.compile("a|b|c");
        Matcher matcher = pattern.matcher("adecgfb");
        /**
         * a
         * c
         * b
         */
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    /**
     * 必须先执行 find()
     */
    @Test
    public void test3() {
        Pattern pattern = Pattern.compile("a|b|c");
        Matcher matcher = pattern.matcher("adecgfb");
        // java.lang.IllegalStateException: No match found
        System.out.println(matcher.group());
    }

    /**
     * 分组
     */
    @Test
    public void test4() {
        String str = "getRegionProvinceResponse{getRegionProvinceResult=anyType{string=黑龙江,3113; string=吉林,3114; string=辽宁,3115; string=内蒙古,3116; string=河北,3117; string=河南,3118; string=山东,3119; string=山西,31110; string=江苏,31111; string=安徽,31112; string=陕西,31113; string=宁夏,31114; string=甘肃,31115; string=青海,31116; string=湖北,31117; string=湖南,31118; string=浙江,31119; string=江西,31120; string=福建,31121; string=贵州,31122; string=四川,31123; string=广东,31124; string=广西,31125; string=云南,31126; string=海南,31127; string=新疆,31128; string=西藏,31129; string=台湾,31130; string=北京,311101; string=上海,311102; string=天津,311103; string=重庆,311104; string=香港,311201; string=澳门,311202; string=钓鱼岛,311203; }; }";
        Pattern pattern = Pattern.compile("string=[^;]+;");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }

    @Test
    public void test5() {
        System.out.println(Pattern.matches(NUMBER_REG, "1"));
        System.out.println(Pattern.matches(NUMBER_REG, "124"));   // true

        System.out.println(Pattern.matches(NUMBER_REG, "1.1"));   // true
        System.out.println(Pattern.matches(NUMBER_REG, "1.1.1")); // false
        System.out.println(Pattern.matches(NUMBER_REG, ".1"));    // false
        System.out.println(Pattern.matches(NUMBER_REG, "1."));    // false
    }

    /**
     * 查找文件 1.txt 中重复出现的子串 {i=xxx}
     */
    @Test
    public void test6() {
        try {
            InputStreamReader isr = new InputStreamReader(ClassLoader.getSystemResourceAsStream("1.txt"));
            BufferedReader br = new BufferedReader(isr);

            Pattern p = Pattern.compile("\\{(.*)\\}");

            List<String> list = new ArrayList<>();

            String line = null;
            int k = 0;
            while ((line=br.readLine())!=null) {
                k++;

                Matcher m = p.matcher(line);
                m.find();
                String split = m.group(1);
                if(list.contains(split)) {
                    log.info(split);
                    break;
                }
                list.add(split);
            }
            log.info("k=" + k);
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @Test
    public void test7() {
        String str1 = "aaa:1 bbbb:2 ccc:3";
        Pattern p1 = Pattern.compile(":1(\\s|$)");
        Pattern p2 = Pattern.compile(":3(\\s|$)");
        Pattern p3 = Pattern.compile(":4(\\s|$)");
        System.out.println(p1.matcher(str1).find()); // true
        System.out.println(p2.matcher(str1).find()); // true
        System.out.println(p3.matcher(str1).find()); // false

        Pattern p4 = Pattern.compile(":1[\\s$]");
        Pattern p5 = Pattern.compile(":3[\\s$]");
        Pattern p6 = Pattern.compile(":4[\\s$]");
        System.out.println(p4.matcher(str1).find()); // true
        System.out.println(p5.matcher(str1).find()); // false
        System.out.println(p6.matcher(str1).find()); // false
    }
}
