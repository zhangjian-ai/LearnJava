## InetAdderss

InetAdderss 类主要用于获取计算机的 主机名 和 主机地址 信息



代码演示：

```java
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
```



## Socket

socket 套接字，可以理解为 服务端 和 客户端 的数据连接通道。分别为 服务端 和 客户端 提供数据传输接口。



### TCP

Java 中主要使用 **ServerSocket类 实现 服务端套接字** 和 **Socekt类 实现 客户端套接字**。



#### 文本传输演示

要求创建服务端和客户端套接字。要求 客户端 使用字节流 向 服务端 发送文本信息；服务端 使用 字符流 向 客户端发送文本信息。

服务端：

```java
package com.zhangjian.网络编程;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        // 1. 创建 服务端套接字，监听 8891 端口
        ServerSocket serverSocket = new ServerSocket(8891);
        System.out.println("服务端启动~~~");

        // 2. 等待客户端连接。获取连接的 套接字
        Socket socket = serverSocket.accept();

        // 3. 获取客户端消息。使用 字节流
        InputStream inputStream = socket.getInputStream();

        byte[] rec = new byte[1024];
        int len;

        // 如果 发送端 不中断单边输出，这里将一直等待 继续传输。除非 人为 指定结束循环的方式
        while ((len = inputStream.read(rec)) != -1){
            System.out.println(new String(rec, 0, len));
        }

        // 4. 获取输出流，使用字符流 向客户端 发送信息
        OutputStream outputStream = socket.getOutputStream();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        writer.write("山不在高，有仙则灵");
        writer.flush();  // 需要 flush 将内容发送到 数据通道


        // 5. 关闭 套接字。socket 中的 IO流 随着 socket 关闭而自动关闭，不需要单独关闭
        socket.close();
        serverSocket.close();
        System.out.println("服务端关闭~~~");
    }
}
```



客户端：

```java
package com.zhangjian.网络编程;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        // 1. 创建客户端套接字，连接 本机 服务
        Socket socket = new Socket(InetAddress.getLocalHost(), 8891);
        System.out.println("客户端启动~~~");

        // 2. 使用字节流 向服务器发送消息
        // 2.1 直接使用 socket 的输出流
//        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write("斯是陋室，惟吾德馨".getBytes(StandardCharsets.UTF_8));
//        socket.shutdownOutput();

        // 2.2 直接使用 IO传输类，保持习惯
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write("斯是陋室，惟吾德".getBytes());
        bufferedOutputStream.flush(); // 如果要调用 shutdownOutput 则可以不手动刷新。
        socket.shutdownOutput(); // 如果接收端，依靠发送端退出接收状态，就需要 调用本方法，中断单边传输


        // 3. 读取 服务端发送的 字符流
        InputStream inputStream = socket.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // 3.1 服务端按行发送，客户端按行读取
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }

        // 4. 关闭套接字
        socket.close();
        System.out.println("客户端退出~~~");
    }
}
```



#### 文件传输演示

将一张图片拷贝到 src 目录下。代码使用 ObjectOutputStream 传输，这样可以同时传递 出数据本身 以外的信息，可以帮助实现 断点续传。

同时，还可以直接使用 BufferedOutputStream 和 BufferedInputStream 直接传输。可以事先获取读取文件 的大小来实现 断点续传



**使用 ObjectOutputStream ：**

```java
package com.zhangjian.网络编程;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

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
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        int total = 0;

        while (true) {

            try {
                Object object = objectInputStream.readObject();

                HashMap<String, Object> map = (HashMap) object;

                // 判断数据类型
                String type = (String) map.get("type");

                // 如果是数据就保存
                if (type.equals("data")) {
                    byte[] rec = (byte[]) map.get("bytes");
                    int len = (int) map.get("len");
                    bufferedOutputStream.write(rec, 0, len);
                    total += len;
                    System.out.println("收到字节总数:" + total);
                    continue;
                }

                // 如果是消息就打印，并反馈客户端图片保存完毕
                if (type.equals("msg")) {
                    String msg = (String) map.get("msg");
                    System.out.println(msg);

                    // 保存图片
                    bufferedOutputStream.close();

                    // 通知客户端接收数据完成
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.write("收到图片，感谢铁子~~~");
                    writer.flush();

                    break;
                }

            } catch (Exception e) {
                System.out.println("等待客户端...");
                Thread.sleep(5000);
            }
        }

        // 关闭套接字
        socket.close();
        serverSocket.close();
    }
}
```

```java
package com.zhangjian.网络编程;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

public class TcpClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8891);

        // 读取本地文件并传输
        String source = "IO/谁不喜欢上班呢.jpeg";

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(source));

        // ObjectOutputStream 中最好使用 BufferedOutputStream 包装一下，直接使用 socket.getOutputStream 会异常
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        // 数据传入完成后，传输结束信息
        int len;
        int total = 0;
        byte[] readData = new byte[4096];

        HashMap<String, Object> map = new HashMap<>();
        map.put("type", "data");

        while ((len = bufferedInputStream.read(readData)) != -1){
            map.put("bytes", readData);
            map.put("len", len);

            objectOutputStream.writeObject(map);
            objectOutputStream.flush();

            total += len;
            System.out.println("发送字节总数数：" + total);

            // ！！！很重要！！！ 由于 每次传递的都是相同对象，因此要重置流中的对象状态，保证下次的数据正常发送过去
            objectOutputStream.reset();
        }

        // 传输结束信息
        map.put("type", "msg");
        map.put("msg", "兄弟，图片给你啦，注意查收");

        objectOutputStream.writeObject(map);
        objectOutputStream.flush();

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
```



**直接使用缓冲流：**

```java
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
```

```java
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
```



### UDP

1. 类 DatagramSocket 和 类DatagramPacket（数据包/数据报）实现了基于UDP的网络协议程序
2. 系统不保证UDP数据包一定能到达目的地，也不能确定什么时候到达
3.  DatagramPacket 对象封装了UDP数据报，其中包含了发送端的IP和端口以及接收端的IP和端口。因此，基于UDP的网络程序无需建立连接就可以直接通信。
4. 数据报 的发送和接收由 DatagramSocket 对象完成，DatagramSocket 接收到 DatagramPacket对象数据报 之后，要进行拆包
5. DatagramSocket 可以绑定监听端口，表示从这个端口接收数据报



代码演示：
```java
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
```

```java
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
```

