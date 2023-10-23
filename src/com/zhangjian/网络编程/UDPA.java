package com.zhangjian.网络编程;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPA {
    public static void main(String[] args) throws IOException {
        // A端
        DatagramSocket socket = new DatagramSocket(8891);
        System.out.println("A端启动~~~");

        // 准备接收 B 端信息并打印
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024); // 表示 该数据包 将最大多长的数据 存入字节数组
        socket.receive(packet); // 这里将阻塞，直到读取到数据

        int length = packet.getLength();  // 数据包获取数据的真实长度
        byte[] data = packet.getData();  // 存放信息的字节数组

        System.out.println(new String(data, 0, length));

        // 向B发送数据
        byte[] bytes = "好的，明天见~~".getBytes();
        packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8892);
        socket.send(packet);

        // 退出
        socket.close();
        System.out.println("A端退出");
    }
}
