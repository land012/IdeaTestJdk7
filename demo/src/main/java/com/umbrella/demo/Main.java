package com.umbrella.demo;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by xudazhou on 2015/11/13.
 */
public class Main {
    public static void main(String[] args) {
        try {
//            InetAddress addr = InetAddress.getLocalHost();
//            System.out.println(String.format("%s-%s-%s-%s",
//                    addr.getHostName(),
//                    addr.getHostAddress(),
//                    addr.isSiteLocalAddress(),
//                    addr.isLoopbackAddress()));

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
