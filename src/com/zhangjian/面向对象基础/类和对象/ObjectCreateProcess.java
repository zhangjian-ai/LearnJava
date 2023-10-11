package com.zhangjian.面向对象基础.类和对象;

public class ObjectCreateProcess {
    public static void main(String[] args) {
        /**
         * 类和对象的内存分配机制
         * 1. 栈。一般存放 基本数据类型的属性、局部变量 和 对象的引用
         * 2. 堆。存放对象（类的实例、数组对象等）
         * 3. 方法区。
         *   3.1、方法区存储了什么东西
         *     3.1.1、类型信息。比如：类class、接口interface、枚举enum、注解annotation
         *     3.1.2、属性(Field)信息。包括: 属性名称、属性类型、属性的修饰符(public， private、protected、static、final、volatile、transient的某个子集)
         *     3.1.3、方法信息。方法的名称、返回类型、参数列表、访问修饰符等
         *     3.1.4、静态变量。静态变量和类关联在一起，随着类的加载而加载，它们成为类数据在逻辑上的一部分
         *     3.1.5、全局常量。被 final static 修饰的变量，不依赖类的加载
         *   3.2、方法区中的常量池
         *     一个java源文件中的类、接口，编译后产生一个字节码文件。
         *     而Java中的字节码需要数据支持，通常这种数据会很大以至于不能直接存到字节码里。
         *     换另一种方式，可以存到常量池，这个字节码包含了指向常量池的引用。
         *
         *     >> 常量池中存放的数据类型有：数量值、字符串值、类引用、字段引用、方法引用 <<
         */


        /**
         * 对象创建过程分析：
         * 1、在方法区加载类(Cat.class)信息，类信息只加载一次，下次再创建新对象不再加载；
         * 2、在堆中分配一个空间(地址：0x1122)，这个空间就可以理解为新创建的对象；
         * 3、对象属性初始化
         *   3.1、默认初始化，将对应属性赋予申明类型的默认值 [age=0 name=null]
         *   3.2、显式初始化，将等号后面的值赋给属性 [age=10 name=null]
         *   3.3、构造器初始化，调用构造方法
         *      3.3.1、age 类型是 int，直接赋值 [age=3]
         *      3.3.2、name 类型是 String，在常量池写入"Tom"（地址：0x1131）并返回地址给到属性name [name->0x1131]
         * 4、返回对象在对中的地址 0x1122 给变量 cat [cat->0x1122]
         * 5、变量cat称为是对上面创建并初始化出来的对象的引用，也可以称为对象名
         */
        Cat cat = new Cat(3, "Tom");


        /**
         * 调用方法时，程序会开辟一个栈空间，存放执行方法需要的临时变量，当方法执行完成后（无关有没有返回值）栈空间销毁，程序回到调用方法的地方
         * 栈的特点，后进先出。因此 当前方法栈推出后，程序就回到上一层栈空间，本程序就是 main栈
         * 方法调用细节：
         * 1、同一个类中的方法可直接调用，不需要借助点号运算符；
         * 2、跨类的方法调用必须是 对象名.方法名() 的形式，且是否能被调用还与被调方法的访问修饰符相关
         */
        cat.sing();

        System.out.println(cat.calculate(23, 43));

    }
}

class Cat {
    int age = 10;
    String name;

    Cat(int pAge, String pName){
        age = pAge;
        name = pName;
    }

    // 无返回值方法
    public void sing() {
        System.out.println(name + "在唱歌");
    }

    // 返回值类型是 int 的计算方法
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}