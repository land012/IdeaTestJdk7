package com.umbrella.demo.java.net;

import org.junit.Test;

import java.net.InetAddress;

/**
 * Created by xudazhou on 2015/11/10.
 */
public class InetAddressDemo {
    @Test
    public void test1() throws Exception {
        System.out.println(InetAddress.getLocalHost().getCanonicalHostName()); // xudazhou-PC
        System.out.println(InetAddress.getLocalHost().getHostName()); // xudazhou-PC
        System.out.println(InetAddress.getLocalHost().getHostAddress()); // 192.168.10.1
    }
}
