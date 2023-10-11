package com.zhangjian.面向对象基础.Object类;

/**
 * Object 中的 hashCode 方法：
 *   1、hashCode 是根据不同对象的内存地址返回的不同的整数，但不能等同于各个对象内存地址；
 *   2、该方法提高了具有hash结构的容器的效率；
 *   3、两个引用如果指向同一个对象，那么哈希值肯定一样；指向不同的对象，那么哈希值肯定也不一样；
 *   4、在集合中，可根据需要重写hashCode方法
 */
public class ObjectHashCode {
    public static void main(String[] args) {
        H h = new H();
        H h1 = new H();
        H h2 = h;

        System.out.println(h.hashCode()); // 312116338
        System.out.println(h1.hashCode()); // 453211571
        System.out.println(h2.hashCode()); // 312116338 和第一个引用指向同一个对象

    }
}

class H{}