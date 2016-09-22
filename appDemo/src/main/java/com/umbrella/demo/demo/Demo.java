package com.umbrella.demo.demo;

import org.junit.Test;

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

}
