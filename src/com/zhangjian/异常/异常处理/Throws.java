package com.zhangjian.异常.异常处理;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * throws 异常处理
 *    1、如果一个方法(执行时)可能产生某种异常，但又不能确定如何处理这种异常，则此方法因显式的申明抛出异常，
 *       表明该方法将不对这些异常进行处理，而由该方法的调用者处理
 *
 *    2、在方法中申明，用 throws 语句，可以申明要抛出的异常列表(多个异常用 , 隔开)，throws 后面的异常应该是 方法中可能产生的异常 或 该异常的父类
 *
 * 使用细节：
 *    1、对于 编译异常，程序中必须处理。要么 try-catch 要么 throws
 *    2、对于 运行时异常，程序中可以不显式的进行处理，默认就是采用 throws 进行处理
 *    3、子类重写父类抛出异常的方法时，子类的方法必须也要抛出异常，且异常的类型 要么和父类异常一致，要么时父类 异常类型的子类
 *    4、throws 和 try-catch 都是处理异常的方法，二者选一即可，不必同时使用
 */
public class Throws {
}

class DemoThrowsFather{
    public void h1() throws IOException{}
}

class DemoThrows extends DemoThrowsFather{
    // 1、对于编译异常必须做处理
    public static void m1() throws IOException{};

    // 1.1、继续 throws 可处理
    public static void m2() throws IOException {
        m1();
    }

    // 1.2、try-catch 也可处理
    public static void m3(){
        try {
            m1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2、对于 运行时异常可不做处理，系统默认会 throws
    public static void f1() throws RuntimeException {};

    public static void f2(){
        f1();
    }

    // 3、子类重写父类抛出异常的方法时，子类的方法必须也要抛出异常，且异常的类型 要么和父类异常一致，要么时父类 异常类型的子类
    @Override
    public void h1() throws EOFException {} // EOFException 是 IOException 的子类

    // 4、方法可以抛出多个异常
    public static void e1() throws ClassNotFoundException, EOFException, FileNotFoundException {}

    // 4.1、调用方可以处理一部分，抛出一部分
    public static void e2() throws ClassNotFoundException{
        try {
            e1();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
