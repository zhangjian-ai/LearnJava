package com.zhangjian.开箱即用.String类;

import java.util.Arrays;

/**
 * 介绍一些常用的String方法
 */
public class StringMethods {
    public static void main(String[] args) {
        String s = new String(" hello world! ");

        // substring 获取子串
        System.out.println("=====substring=====");
        System.out.println(s.substring(3)); // 从所引处截取到最后
        System.out.println(s.substring(3, 7)); // 结束索引不包含

        // length 获取字符串长度，即字符个数
        System.out.println("=====length=====");
        System.out.println(s.length());

        // strip 去除字符串前后的空格
        System.out.println("=====strip=====");
        System.out.println(s.strip());

        // replace 子串替换
        System.out.println("=====replace=====");
        System.out.println(s.replace("hello", "haha"));

        // indexOf 返回 字符 或 子串首次在 字符串中出现的位置索引
        System.out.println("=====indexOf=====");
        System.out.println(s.indexOf('h'));
        System.out.println(s.indexOf("world"));

        // charAt 返回指定索引处的字符
        System.out.println("=====charAt=====");
        System.out.println(s.charAt(3));

        // startsWith endsWith 判断字符串是不是以某个子串开头或结尾
        System.out.println("=====startsWith endsWith=====");
        System.out.println(s.startsWith(" hell"));
        System.out.println(s.endsWith("d!"));

        // intern 返回 String对象中 value 属性指向的 在常量池中的 字符串常量
        System.out.println("=====intern=====");
        System.out.println(s.intern());

        // equals 重写了Object的equals，用于判断两个字符串对象，值是否相等：
        //        1.先判断入参和当前对象是否是同一个对象，如果是直接返回true
        //        2.如果1为false，当入参是String对象，如果是就遍历对比每一个字符。如果字符串值相等返回true，否则返回false
        //        3.如果1为false，入参不是String对象，直接返回false
        System.out.println("=====equals=====");
        System.out.println(s.equals(" hello world! "));
        System.out.println(s.equals(" hello world!"));


        // hashCode 重写了Object的hashCode，返回String对象的hash码值
        //          计算hash值时，使用的是 String 中的value属性
        //          这就意味着不同的String对象，只要他们的字符序列一样，那么hashCode也一样
        System.out.println("=====hashCode=====");
        System.out.println(new String("天外飞仙").hashCode());
        System.out.println("天外飞仙".hashCode());


        // toCharArray 将字符串转换为 字符数组
        System.out.println("=====toCharArray=====");
        System.out.println(s.toCharArray());

        // split 根据正则切割字符串，返回一个 String数组
        System.out.println("=====split=====");
        String[] s1 = s.split(" ");
        System.out.println(Arrays.toString(s1));

    }
}
