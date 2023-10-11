package com.zhangjian.异常.常见的运行时异常;

/**
 * ClassCastException 类型转换异常
 *   当时图将 对象 强制转换为 运行类型的子类 或 其他不相关的类型 时(类型转换只能在当前运行类型及向上的多个父类间进行)，抛出该异常。
 */
public class ClassCastException {
    public static void main(String[] args) {
        A b = new B();  // 向上转型
        B b1 = (B)b; // 向下转型 OK
//        D d1 = (D)b; // 向下转型，但是 是 运行类型的子类，不可转
        C c1 = (C)b; // 类C 和 类B 毫不相干，不可转
    }
}

class A {};
class B extends A {};
class C extends A {};
class D extends B {};
