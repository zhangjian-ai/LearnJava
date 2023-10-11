package com.zhangjian.异常.常见的运行时异常;

/**
 * NullPointerException 空指针异常
 *    在程序中试图在使用对象的地方，使用了 null ，就会抛出该异常。
 */
public class NullPointerException extends Throwable {
    public static void main(String[] args) {
        String name = null;
        System.out.println(name.getClass());  // name 运行类型不是 String
    }
}
