package com.zhangjian.面向对象基础.Object类;

import java.util.Objects;

/**
 * 在java中Object类，是所有类的顶级父类。
 *
 * 注意：Object中几乎所有方法都是public(finalize是protected)，就是为了方便子类使用及重写
 *
 *
 * 下面介绍几个高频使用或被重写的方法
 */
public class ObjectClass {
    public static void main(String[] args) {
        Object o = new Object();

        /**
         * toString()
         *   当一个对象被直接输出时，jvm会调用该对象的 toString 方法，真正打印的是toString的返回值
         *   本方法经常被重写
         */
        System.out.println(o);
        System.out.println(o.toString());

        /**
         * hashCode()
         *   返回当前对象的 hashCode 码值，是根据对象的内存地址经过一系列算法得出的，不同对象的 hashCode 码值可能相同
         *   HashMap 中就是使用该方法，结合其他算法来确定 节点对象(HashMap中的 Node/TreeNode) 在数组中索引的位置
         */
        System.out.println(o.hashCode());

        /**
         * equals()
         *   判断两个对象是否是同一个对象
         *   本方法经常被重写，内置的String类，就从写了该方法，用于判断两个 String对象的 值 是否相等
         */
        System.out.println(o.equals(new Object())); // false

        /**
         * getClass()
         *   返回当前对象的 运行类型
         */
        System.out.println(o.getClass()); // class java.lang.Object



    }
}
