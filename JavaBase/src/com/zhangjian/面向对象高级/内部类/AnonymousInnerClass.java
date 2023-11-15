package com.zhangjian.面向对象高级.内部类;

/**
 * 匿名内部类：
 *    匿名内部类首先是一个局部内部类，因此此匿名内部类通常定义在外部类的局部位置，比如 方法体 或 代码块 中，且没有名字。
 *    另外，匿名内部类在创建后立即完成实例化，之后将不能再次实例化。因此匿名内部类只能被使用一次，当然首次实例化出来的对象可以在作用域内多次使用
 *    匿名内部类的主要作用就是减少硬编码，使编程过程更加灵活
 *
 * 语法：
 *    new 类或接口(参数列表){
 *       // 类体
 *    };
 *
 * 语法说明(拆解)：
 *   1、new 关键字表示实例化后面的 匿名内部类
 *   2、类或接口 二者选一即可。表示 匿名内部类 继承了这个类 或 实现了这个接口
 *   3、(参数列表) 这里就是实例化时给构造器传递的参数，如果 父类是默认构造器 或 实现的是接口，那就没有参数列表，保留 () 即可
 *   4、{} 匿名内部类的类体，既然是 继承 或 实现 的关系，那么类体中必然要实现 父类/接口 的抽象方法，当然也可以定义自己的方法
 *   5、将 匿名内部类 理解为 继承父类/实现接口 ，也就意味着 匿名内部类的实例 同样具有和普通实例一样的多态特性
 *   6、特别注意，匿名内部类中 不能有 构造方法
 *
 */
public class AnonymousInnerClass {
    public static void main(String[] args) {
        new Demo().demoAnonymous();
    }
}


class Demo{
    public void demoAnonymous(){
        System.out.println("本方法演示一下 匿名内部类");

        // timer 的编译类型 Timer，运行类型是 Demo$1 (这个名字是jvm取的，多个匿名内部类将增加编号 Demo$2 Demo$3 ...)
        Timer timer = new Timer() {
            // 既然是子类，就要实现抽象父类的抽象方法
            @Override
            public void desc() {
                System.out.println("匿名内部类的实例 向上转型...");
            }

            // 当然子类可以有自己的方法。但是此处由于是匿名的子类，没办法向下转型，timer 将永远无法使用该方法
            public void myDesc(){
                System.out.println("匿名内部类自己的描述");
            }
        };

        // 输出timer的 运行类型
        System.out.println("timer 运行类型是 " + timer.getClass());

        // 向上转型后，只能调用 编译类型 中的成员
        System.out.println(timer.song);
        timer.desc();


        /*
        匿名内部类通常可作为方法调用时的参数传入，可以很大程度的简化代码
        下面演示两个示例
         */
        timer.remind(new Bell() {
            @Override
            public void ring() {
                System.out.println("手机铃声开始播放");
            }
        });

        timer.remind(new Bell() {
            @Override
            public void ring() {
                System.out.println("集体广播开始播放");
            }
        });

        // 匿名内部类可以定义自己的方法，也可以调用。但通常没必要这么做，因为通常都会因为向上转型的原因而不能使用
        new Food("大米"){ // 通过参数直接完成父类的初始化，但 匿名内部类 不能有自己的构造器，可以有 非静态的代码块
            {
                System.out.println(getName() + " 开始初始化.."); // 使用父类的继承过来的方法
            }
            public void color(){
                System.out.println("这是大米的颜色-白色");
            }
        }.color();
    }
}

interface Bell{
    void ring();
}

abstract class Timer{
    public String song = "披着羊皮的狼";

    public abstract void desc();

    public void remind(Bell bell){
        bell.ring();
    }
}

class Food{
    private String name;

    {
        System.out.println("食物 开始初始化..");
    }

    public String getName() {
        return name;
    }

    public Food(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("这是一种食物");
    }
}