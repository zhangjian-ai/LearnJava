package com.zhangjian.面向对象基础.Object类;

/**
 * Object 中的 finalize 方法：
 *   1、当对象被回收时，系统会先自动调用该对象的 finalize 方法。子类可以重写该方法，做一些 释放资源 的操作；
 *   2、当某个对象没有任何引用时，则 JVM 就认为这个对象是一个垃圾对象，会被垃圾回收程序销毁；
 *   3、垃圾回收机制的出发，是由系统来决定的（jvm中的GC算法）。我们也可以通过 System.gc() 主动触发垃圾回收，该过程是非阻塞的
 */
public class ObjectFinalize {
    public static void main(String[] args) throws InterruptedException {
        Tiger jiJi = new Tiger("JiJi"); // 此时Tiger被属性 jiJi 引用

        // 将jiJi置为空，那么Tiger的引用计数为0，将被视为垃圾
        jiJi = null;

        // 由于我们要观测回收效果，所以手动触发一次垃圾回收
        System.gc();

        // 等一会，免得垃圾回收没结束。因为垃圾回收是非阻塞的
        Thread.sleep(3000);

        System.out.println("程序结束...");
    }
}

class Tiger{
    private String name;

    public Tiger(String name) {
        this.name = name;
    }

    // 重写finalize
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Tiger " + name + " 被回收了...");
    }
}