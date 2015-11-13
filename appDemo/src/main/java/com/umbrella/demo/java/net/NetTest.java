package com.umbrella.demo.java.net;

import org.junit.Test;

import java.net.*;
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
            System.out.println(new String(localHost.getAddress(), "gbk")); // ��x
        } catch (Exception e) {
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
            System.out.println("===================================================");
            while (enumeration.hasMoreElements()) {
                NetworkInterface ni = enumeration.nextElement();
                if(!ni.isUp()) {
                    continue;
                }
                System.out.println(String.format("%s-%s-%s-%s-%s-%s",
                        ni.getName(),
                        ni.getDisplayName(),
                        ni.isUp(),
                        ni.isVirtual(),
                        ni.isLoopback(),
                        ni.isPointToPoint()));
                Enumeration<InetAddress> inets = ni.getInetAddresses();
                System.out.println("--------");
                while (inets.hasMoreElements()) {
                    InetAddress inetAddr = inets.nextElement();
                    if(inetAddr instanceof Inet6Address) continue;
                    StringBuilder res = new StringBuilder();
                    res.append(inetAddr.getHostName())
                            .append("-")
                            .append(inetAddr.getHostAddress())
                            .append("-")
                            .append(inetAddr.isLinkLocalAddress())
                            .append("-")
                            .append(inetAddr.isSiteLocalAddress())
                            .append("-")
                            .append(inetAddr.isLoopbackAddress());
                    System.out.println(res.toString());
                }
                System.out.println("===================================================");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
