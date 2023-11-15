package com.zhangjian.设计模式.单例模式;

/**
 * 单例模式：
 *    单例模式就是在程序运行过程中，某个类的实例只能存在一个，并且该类只提供一个取得其对象实例的方法。
 *
 * 单例模式实现步骤：
 *    1、构造器私有化(禁止外部直接new一个新对象)
 *    2、在类的内部创建对象实例
 *    3、对外提供一个公共的静态方法来获取实例(方法名：getInstance)
 *
 * 实现方式：
 *  1、饿汉式
 *     实现方式：在类加载时，创建好实例对象。
 *     缺点：只要类加载就会创建实例，当程序只使用类中其他静态属性时，会有明显的资源浪费
 *  2、懒汉式
 *     实现方式：在用户调用 getInstance 时，才创建实例，如果不调用该方法或只使用类中其他静态成员，则不会创建实例对象
 *     缺点：存在线程安全问题，多个线程可能触发多次实例的创建
 *
 *
 */
public class Singleton {
    public static void main(String[] args) {
        // 饿汉
        SingletonHunger instance1 = SingletonHunger.getInstance();
        SingletonHunger instance2 = SingletonHunger.getInstance();

        System.out.println(instance1);
        System.out.println(instance2);

        // 懒汉
        SingletonLazy instance3 = SingletonLazy.getInstance();
        SingletonLazy instance4 = SingletonLazy.getInstance();

        System.out.println(instance3);
        System.out.println(instance4);



    }
}


class SingletonHunger{
    // 静态属性 instance 记录实例
    private static SingletonHunger instance = new SingletonHunger("饿汉仔");

    private String name;

    private SingletonHunger(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SingletonHunger{" +
                "name='" + name + '\'' +
                '}';
    }

    public static SingletonHunger getInstance() {
        return instance;
    }
}


class SingletonLazy{
    // 静态属性 instance 记录实例
    private static SingletonLazy instance;

    private String name;

    private SingletonLazy(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SingletonLazy{" +
                "name='" + name + '\'' +
                '}';
    }

    public static SingletonLazy getInstance() {
        if (instance == null){
            instance = new SingletonLazy("懒汉仔");
        }
        return instance;
    }
}