package com.zhangjian.IO流;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class IO {
    public static void main(String[] args) throws IOException {
        // 获取ClassLoader对象
        ClassLoader classLoader = IO.class.getClassLoader();

        // 获取资源 返回一个 URL示例
        URL resource = classLoader.getResource("JavaBase.iml"); // 相对路径，获得此类所在包下的资源，即 com 所在路径
        System.out.println(resource.getPath()); // 获取资源在操作系统的绝对路径

        // 以 字节流 形式获取资源
        InputStream stream = classLoader.getResourceAsStream("/JavaBase/out/JavaBase.iml");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }
    }
}
