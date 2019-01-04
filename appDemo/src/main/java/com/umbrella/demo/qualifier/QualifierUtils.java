package com.umbrella.demo.qualifier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create by xudazhou 2019/1/4
 */
public class QualifierUtils {

    public static String underToCamel(String word) {
        Pattern pattern = Pattern.compile("_(\\w)");
        Matcher matcher = pattern.matcher(word);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }

        System.out.println(sb);

        matcher.appendTail(sb);
        return sb.toString();
    }
}
