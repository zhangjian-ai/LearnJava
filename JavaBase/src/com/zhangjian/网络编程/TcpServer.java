package com.zhangjian.网络编程;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8891);
        System.out.println("服务器启动成功~~~");

        // 等待客户端连接
        Socket socket = serverSocket.accept();

        // 准备接收数据，并保存到目标文件
        String destPath = "src/上班.jpeg";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destPath));

        // 使用对象流处理数据传输过程中的状态
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());

        int len;
        int total = 0;
        byte[] readData = new byte[4096];

        while ((len = bufferedInputStream.read(readData)) != -1){
            bufferedOutputStream.write(readData, 0, len);
            total += len;
            System.out.println("接收字节总数：" + total);
        }

        bufferedOutputStream.flush();

        // 通知客户端接收数据完成
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("收到图片，感谢铁子~~~");
        writer.flush();

        // 关闭套接字
        socket.close();
        serverSocket.close();
    }
}
