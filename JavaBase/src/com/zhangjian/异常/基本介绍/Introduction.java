package com.zhangjian.异常.基本介绍;

/**
 * 基本概念
 *    Java语言中，将程序执行中发生的不正常情况称为"异常"。(开发过程中的语法错误和逻辑错误不是异常)
 *
 * 执行过程中所发生的异常事件可分为两大类(Error 和 Exception 都是 Throwable 子类，Throwable 是整个异常体系中的基类)：
 *    1、Error 错误。 Java 虚拟机无法解决的严重问题，将会直接导致程序崩溃
 *       如：JVM系统内部异常、StackOverflowError(栈溢出)、OOM(out of memory)
 *
 *    2、Exception 异常。因编程错误或偶然的外在因素导致的一般性问题，可以使用代码针对性的进行处理。
 *       如：空指针访问、试图读取不存在的文件、网络连接中断等等
 *       Exception 又分为两大类：
 *         2.1、运行时异常  程序运行时，发生的异常，代码中可不做处理
 *         2.2、编译时异常  编译java源文件时，编译器(javac)检查出的异常，程序代码必须做处理，下面是常见的编译异常。[在IDE中，编程时就会体现出来]
 *            - SQLException  操作数据库时出现的异常
 *            - IOException  处理系统输入输出时发生的异常
 *            - FileNotFoundException  操作一个不存在的文件，引发的异常
 *            - ClassNotFoundException  加载一个不存在的类，引发的异常
 *            - EOFException  试图操作一个已经到文件末尾的文件时，引发的异常 IOException 的子类
 *            - IllegalArgumentException 参数异常。比如 用 String 给 int[] 赋值
 *
 */
public class Introduction {
    public static void main(String[] args) {
        try {
            int a= 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


