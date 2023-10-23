package com.zhangjian.IO流;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

public class IO {

    @Test
    public void readProperties() throws IOException{
        // 1. 创建实例
        Properties properties = new Properties();

        // 2.加载指定配置文件
        properties.load(new FileReader("IO/mysql.properties"));

        // 3. 输出所有 k-v
        properties.list(System.out);

        // 4. 根据 key 获取对应的值
        System.out.println(properties.getProperty("host"));
        System.out.println(properties.getProperty("port"));
        System.out.println(properties.getProperty("user"));
        System.out.println(properties.getProperty("pwd"));
        System.out.println(properties.getProperty("comment"));
    }

    @Test
    public void configProperties() throws IOException{
        // 1. 创建实例
        Properties properties = new Properties();

        // 2.加载指定配置文件
        properties.load(new FileReader("IO/mysql.properties"));

        // 3. 增 删 改 Properties 中的键值
        properties.setProperty("secret", "admin9527");
        properties.remove("port");
        properties.setProperty("comment", "你方唱罢我登场");

        // 4. 保存到文件
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("IO/mysql.properties"), "utf8");
        properties.store(writer, null);

        System.out.println("保存成功");
    }
}
