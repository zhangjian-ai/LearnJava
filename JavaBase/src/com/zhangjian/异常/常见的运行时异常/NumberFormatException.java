package com.zhangjian.异常.常见的运行时异常;

/**
 * NumberFormatException 数值格式化异常
 *    当应用程序试图将字符串转换成一种数值类型，字符串中包含无法转换的字符时，抛出该异常
 */
public class NumberFormatException {
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("367232")); // 正常的数值字符串可以解析

        // 其他均不能转换
//        System.out.println(Integer.parseInt("a"));
        System.out.println(Integer.parseInt("吃饭"));
    }
}
