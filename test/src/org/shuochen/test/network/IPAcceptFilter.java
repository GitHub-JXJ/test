package org.shuochen.test.network;

/**
* Consider @FunctionalInterface for JDK8
*
*/
public interface IPAcceptFilter {
    public String IPv6KeyWord = ":";
    public boolean accept(String ipAddress);
}