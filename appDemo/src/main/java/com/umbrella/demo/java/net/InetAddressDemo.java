package com.umbrella.demo.java.net;

import org.junit.Test;

import java.net.InetAddress;

/**
 * Created by xudazhou on 2015/11/10.
 */
public class InetAddressDemo {

    /**
     * 获取本机IP
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println(localhost.getCanonicalHostName()); // xudazhou-PC
        System.out.println(localhost.getHostName()); // xudazhou-PC
        // 在 Linux 下无法获取到正确的ip，因为这个方法获取 /etc/hostss 下的 localhost
        System.out.println(localhost.getHostAddress()); // 192.168.10.1
        System.out.println(System.getenv("COMPUTERNAME")); // BARBATOS
    }
}
