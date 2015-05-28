package com.umbrella.java.demo;

import org.junit.Test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by 大洲 on 15-5-27.
 */
public class NetTest {
    @Test
    public void test1() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            InetAddress loopback = InetAddress.getLoopbackAddress();
            System.out.println(localHost.getHostAddress()); // 192.168.120.1 VMnet8的地址
            System.out.println(loopback.getHostAddress()); // 127.0.0.1

            System.out.println(localHost.getCanonicalHostName()); // landpc
            System.out.println(localHost.getHostName()); // landpc
            System.out.println(new String(localHost.getAddress())); // ��x
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 机器有多张网卡
     */
    @Test
    public void test2() {
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface ni = enumeration.nextElement();
                System.out.println(ni);
                System.out.println(ni.isVirtual());
                Enumeration<InetAddress> inets = ni.getInetAddresses();
                while (inets.hasMoreElements()) {
                    System.out.println(inets.nextElement().getHostAddress());
                }
                System.out.println("===================================================");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
