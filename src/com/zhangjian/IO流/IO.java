package com.zhangjian.IO流;

import org.junit.jupiter.api.Test;

import java.io.*;

public class IO {

    @Test
    public void writeFile(){
        String filePath = "/Users/zhangjian1138/IdeaProjects/LearnJava/IO/news02.txt";

        FileWriter writer = null;

        try {
            // 已覆盖的方式写入。原文件的内容将被覆盖
            writer = new FileWriter(filePath);

            // 1. 写入单个字符。写数字会转成对应的字符
            writer.write('A');
            writer.write(98);

            // 2. 写入字符数组
            char[] chars = {'h', 'e', 'l', 'l', 'o'};
            writer.write(chars);

            // 3. 写入字符数组指定长度
            writer.write(chars, 2, 3);

            // 4. 写入字符串
            writer.write(" 我自狂歌空度日 ");

            // 5. 写入字符串 指定长度
            writer.write(" 飞扬跋扈为谁雄", 5, 3);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null){
                    writer.close();
                    System.out.println("数据写入完成");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
