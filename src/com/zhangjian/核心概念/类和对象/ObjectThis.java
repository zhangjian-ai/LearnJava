package com.zhangjian.核心概念.类和对象;


public class ObjectThis {
    public static void main(String[] args) {
        /**
         * Java 虚拟机为每个对象分配this，代表当前对象。同py中的self关键字，但 this 不会显式的写在方法的形参里面。
         *
         * 使用细节：
         * 1、this关键字可以用来访问本类的属性、方法、构造器；
         * 2、this可用于区分当前类的属性和局部变量；
         * 3、访问成员方法的语法：this.方法名(形参列表)；
         * 4、访问构造器的语法：this(参数列表) [注意：本语法只能用于在构造器中访问另一个构造器，且本语句要放在代码块的第一行]；
         * 5、this只能在类定义的方法中使用，不能再方法外部使用。
         */

        T t = new T();

        t.f3();
        t.f2();
    }

}


class T{
    String name;

    //构造器中访问构造器
    public T(){
        // this() 语法必须放在第一行，通过this对重载的构造器进行访问
        this("Jack");
        System.out.println("T() 执行");
    }

    public T(String name){
        // 当形参和属性同名时，直接使用 name 那么将根据就近原则使用形参的 name，如果希望给属性name赋值，则需要借助this关键字
        this.name = name;

        System.out.println("T(String name) 执行");
    }

    public void f1(){
        System.out.println("f1() 方法执行");
    }

    public void f2(){
        System.out.println("f2() 方法执行");

        // 第一种方式，直接调用
        f1();

        // 第二种方式，通过 this 调用。当存在继承且父类也有f1() 方法时，使用this表示用自己的，使用super表示用父类的。这里没有继承，仍然可以显式指定
        this.f1();
    }

    public void f3(){
        String name = "花好月圆";

        // 直接输出，采用就近原则，访问的事局部变量name
        System.out.println("直接输出name，值=" + name);

        // 使用this，表示访问属性name
        System.out.println("使用this输出name，值=" + this.name);

    }


}