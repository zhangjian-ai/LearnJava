package com.zhangjian.网络编程;

import com.zhangjian.IO流.IO;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        Server.run();
    }
}

/**
 * 基于 TCP 协议解析 http 报文，并返回一个表格
 */
class Server {
    public static void run() throws IOException {
        // 创建套接字
        ServerSocket serverSocket = new ServerSocket(8080);

        // 创建线程池，最多同时处理10个请求
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 10, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>(30));

        // 等待访问
        while (true) {
            // 拿到一个远程连接
            Socket socket = serverSocket.accept();
            System.out.println(socket.getRemoteSocketAddress());

            // lambda 表达式，实现 Runnable
            executor.submit(() -> {
                try {
                    handler(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void handler(Socket socket) throws IOException {
        // try() 是一个语法糖。提供了一种对资源的上下文管理，代码块执行结束后，会自动关闭资源
        try (socket) {
            handle(socket.getInputStream(), socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("连接断开~");
        }
    }

    public static void handle(InputStream input, OutputStream output) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

        boolean requestOK = false;

        // 1. 解析 请求行
        String s = reader.readLine();
        if(s.startsWith("GET / HTTP/1.")) requestOK = true;

        // 2. 解析请求头
        for (;;){ // 等价于 while true
            String line = reader.readLine();
            if(line.isEmpty()) break;

            System.out.println(line);
        }

        System.out.println(requestOK ? "Request OK~" : "Request Error~");

        // 3. 准备响应结果
        if (!requestOK){
            writer.write("HTTP/1.0 404 Not Found\r\n");
            writer.write("Content-Length:0\r\n");
            writer.write("\r\n");
        }else {
            // 返回数据文件。使用当前类对象，加载文件
            InputStream resource = HttpServer.class.getResourceAsStream("price.html");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource));

//            BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/zhangjian1138/IdeaProjects/LearnJava/JavaBase/src/com/zhangjian/网络编程/price.html"));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) builder.append(line);
            bufferedReader.close();
            String content = builder.toString();

            // 响应行
            writer.write("HTTP/1.0 200 OK\r\n");

            // 响应头
            writer.write("Connection: keep-alive\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length:" + content.length() + "\r\n");
            writer.write("\r\n"); // 响应头和响应体之间要有一个空行
            writer.write(content);
        }
        writer.flush();
    }
}



