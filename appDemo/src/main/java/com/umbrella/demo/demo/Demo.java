package com.umbrella.demo.demo;

import java.net.InetAddress;

/**
 * Created by 大洲 on 14-11-5.
 */
public class Demo {
    public static void main(String[] args) {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void test2() {
//        DecimalFormat df = new DecimalFormat("0000");
//        for(int i=1; i<=225; i++) {
//            System.out.println("<img src=\"images/" + df.format(i) + ".png\" />");
//        }
//    }
//
//    @Test
//    public void test3() {
//        for(int i=1; i<=775; i++) {
//            System.out.println("<img src=\"images/" + i + ".jpg\" />");
//            System.out.println("<hr />");
//        }
//    }

}
