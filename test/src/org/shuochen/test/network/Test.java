package org.shuochen.test.network;

public class Test {
    public static void main(String[] args) {
        System.out.println(ServerHost.getInstance().getExtranetIPAddress());
        System.out.println(ServerHost.getInstance().getExtranetIPv4Address());
        System.out.println(ServerHost.getInstance().getExtranetIPv6Address());
    }

}