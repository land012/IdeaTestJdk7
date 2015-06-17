package com.umbrella.demo.java.regexp;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 大洲 on 15-4-20.
 */
public class RegExpDemo {
    @Test
    public void test1() {
        System.out.println(Pattern.matches("a|b|c", "adef")); // false
        System.out.println(Pattern.matches("^a.*", "adef")); // true
        System.out.println(Pattern.compile("a|b|c").matcher("adef").matches()); // false
        System.out.println(Pattern.compile("^a.*").matcher("adef").matches()); // true
        System.out.println(Pattern.compile("a|b|c").matcher("adef").find()); // true
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
}
