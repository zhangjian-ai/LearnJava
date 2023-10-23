package com.zhangjian.网络编程;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8891);

        // 读取本地文件并传输
        String source = "IO/谁不喜欢上班呢.jpeg";

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(source));

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());

        int len;
        int total = 0;
        byte[] readData = new byte[4096];

        while ((len = bufferedInputStream.read(readData)) != -1){
            bufferedOutputStream.write(readData, 0, len);
            bufferedOutputStream.flush();
            total += len;
            System.out.println("发送字节总数：" + total);
        }

        socket.shutdownOutput();
        System.out.println("图片发送完毕");

        // 关闭文件读取的流
        bufferedInputStream.close();

        // 接收服务端的反馈信息
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(bufferedReader.readLine());

        // 关闭套接字
        socket.close();
    }
}