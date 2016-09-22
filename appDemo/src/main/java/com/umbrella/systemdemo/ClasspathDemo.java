package com.umbrella.systemdemo;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.net.URL;

/**
 * Created by xudazhou on 2015/8/25.
 */
public class ClasspathDemo {
    /**
     * 获取 classpath
     */
    @Test
    public void test1() {
//        // /D:/_idea/TestApp/appDemo/target/classes/
//        System.out.println(ClassLoader.getSystemResource("").getPath());
//
//        // /D:/_idea/TestApp/appDemo/target/classes/
//        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
//
//        // /D:/_idea/TestApp/appDemo/target/classes/com/umbrella/demo/java/demo/
//        System.out.println(this.getClass().getResource("").getPath());
//
//        // /D:/_idea/TestApp/appDemo/target/classes/
//        System.out.println(this.getClass().getResource("/").getPath());

        // 所以 jar路径
        String java_class_path = System.getProperty("java.class.path");
        System.out.println(java_class_path);
    }

    /**
     * 类所在 jar文件 的路径
     */
    @Test
    public void test2() {
        URL location = StringUtils.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getPath()); // /D:/_mvn_repository/org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2.jar
        System.out.println(location.getFile()); // /D:/_mvn_repository/org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2.jar

        // file:/D:/_mvn_repository/org/apache/commons/commons-lang3/3.3.2/commons-lang3-3.3.2.jar!/org/apache/commons/lang3/
        System.out.println(StringUtils.class.getResource("").getPath());
        // /D:/_idea/TestApp/appDemo/target/classes/
        System.out.println(StringUtils.class.getResource("/").getPath());
    }
}
