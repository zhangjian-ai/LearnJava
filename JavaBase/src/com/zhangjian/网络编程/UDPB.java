package com.zhangjian.网络编程;

import com.zhangjian.IO流.IO;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPB {
    public static void main(String[] args) throws IOException {
        // B 端
        DatagramSocket socket = new DatagramSocket(8892);
        System.out.println("B端启动~~~");

        // 向A发送数据
        byte[] bytes = "明天出去吃大餐".getBytes();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8891);
        socket.send(packet);

        // 准备接收 A 端信息并打印
        packet = new DatagramPacket(new byte[1024], 1024); // 表示 该数据包 将最大多长的数据 存入字节数组
        socket.receive(packet); // 这里将阻塞，直到读取到数据

        int length = packet.getLength();  // 数据包获取数据的真实长度
        byte[] data = packet.getData();  // 存放信息的字节数组

        System.out.println(new String(data, 0, length));

        // 退出
        socket.close();
        System.out.println("B端退出");
    }
}
