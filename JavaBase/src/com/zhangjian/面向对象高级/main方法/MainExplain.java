package com.zhangjian.面向对象高级.main方法;


/**
 * 深入理解main方法
 *
 * main方法形式：
 *   pubLic static void main(String[] args){}
 *
 * 说明：
 *   1、main方法是由java虚拟机（JVM）调用
 *   2、jvm和main方法不在同一个包，因此main方法访问权限必须是 public
 *   3、jvm调用main方法时，不需要创建实例对象，因此main方法必须是静态的 static
 *   4、jvm调用main方法后，不会处理main方法的任何返回值，因此返回类型也默认是 void
 *   5、main 方法接收一个 字符串数组 的形参，形参名称为 args，用于接收程序执行时传递进来的参数
 *   6、java 程序执行过程
 *     6.1、java 文件先由 javac(java compile: java编译机) 编译java文件，生成同名但后缀为 .class 的字节码文件
 *     6.2 然后再由 java 来执行程序，形式 java 目标class文件名 参数1 参数2 参数3 ...
 */
public class MainExplain {
    public static void main(String[] args) {
        System.out.println("看看传递进来的参数有哪些");
        for (String arg:
             args) {
            System.out.println(arg);
        }
    }
}
