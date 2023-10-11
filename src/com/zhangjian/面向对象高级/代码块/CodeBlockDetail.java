package com.zhangjian.面向对象高级.代码块;

/**
 * 代码块使用细节：
 *   1、构造器(构造方法)的最前面其实隐含了 super() 和 普通代码块的调用
 *   2、静态代码块由于在类加载时调用，因此 静态代码块 肯定优先于 普通代码块 和 构造器 执行
 *   3、创建一个子类对象时，他们的 静态代码块、静态属性、普通代码块、普通属性 调用的顺序如下：
 *     3.1、父类的 静态代码块执行 和 静态属性初始化 [按定义顺序执行]
 *     3.2、子类的 静态代码块执行 和 静态属性初始化 [按定义顺序执行]
 *     3.3、父类的 普通代码块执行 和 普通属性初始化 [按定义顺序执行]
 *     3.4、父类的 构造方法执行
 *     3.5、子类的 普通代码块执行 和 普通属性初始化 [按定义顺序执行]
 *     3.6、子类的 构造方法执行
 *
 *   4、静态代码块只能直接调用静态成员(静态属性、静态方法)，普通代码块可以调用任意成员
 *
 */
public class CodeBlockDetail {
    public static void main(String[] args) {
        BBB 王麻子 = new BBB("王麻子");
    }
}


class BBB extends AAA{
    // 静态属性
    public static double price = 15.8;

    // 静态代码块
    static {
        System.out.println("子类静态代码块");
    }

    // 实例属性
    private int age = getAge();

    public BBB(String nickname) {
        super(nickname);
        // 普通代码块调用 和 普通属性初始化 在这里完成
        System.out.println("子类构造器执行");
    }

    // 普通代码块
    {
        System.out.println("子类普通代码块");
    }

    public static String getName(){
        System.out.println("子类的静态方法，给 子类静态属性赋值");
        return "JackSon";
    }

    public int getAge(){
        System.out.println("子类普通属性 age 初始化");
        return 18;
    }
}

class AAA{
    // 静态属性
    public static String name = getName();

    // 静态代码块
    static {
        System.out.println("父类静态代码块");
    }

    // 实例属性
    private String nickname;

    public AAA(String nickname) {
        // super()
        // 普通代码块调用 和 普通属性初始化 在这里完成
        System.out.println("父类构造方法执行");
        this.nickname = nickname;
    }

    // 普通代码块
    {
        System.out.println("父类普通代码块");
    }

    public static String getName(){
        System.out.println("父类静态属性 name 初始化");
        return "Jack";
    }

}
