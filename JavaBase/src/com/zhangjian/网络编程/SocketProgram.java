package com.zhangjian.网络编程;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SocketProgram {
    public static void main(String[] args) throws UnknownHostException {

        // 1. 获取 InetAddress 对象

        // 1.1 获取本机的 InetAddress 对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        // 1.2 通过 域名 获取 InetAddress 对象
        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress);

        // 1.3 通过 主机名 获取 InetAddress 对象
        InetAddress inetAddress1 = InetAddress.getByName("SeekerdeMacBook-Pro.local");
        System.out.println(inetAddress1);

        // 2. 通过 InetAddress 对象 获取 主机名 和 主机地址
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getHostAddress());
    }
}
