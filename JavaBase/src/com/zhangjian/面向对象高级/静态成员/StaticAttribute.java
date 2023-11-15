package com.zhangjian.面向对象高级.静态成员;

/**
 * 静态属性：
 *   静态属性也叫静态变量，又叫类属性/类变量。是该类的所有对象共享的变量，任何一个类的实例对象去访问他时，取到的都是相同的值；
 *   同样任何一个类的实例对象去修改它时，修改的也是同一个变量。
 *
 * 语法：
 *   访问修饰符 static 数据类型 变量名;  [推荐]
 *   static 访问修饰符 数据类型 变量名;
 *
 * 静态变量访问方式：
 *   类名.静态变量名  [推荐]
 *   实例名.静态变量名
 *
 * 静态变量使用细节：
 *   1、当需要所有对象共享一个属性时，或类中需要一个可以直接访问的属性时，就可以考虑使用 静态变量（类变量、类属性、静态属性）；
 *   2、加上 static 的属性称为 类属性/类变量/静态属性/静态变量，没有加的称为 实例属性/非静态变量/普通变量；
 *   3、类属性 是所有实例共享的；实例属性是对象独有的，对象之间相互隔离；
 *   4、类属性 既可以通过 类名 调用，也可以通过 实例名 调用；实例属性只能通过 实例名 调用；
 *   5、类属性 在类加载时就完成了初始化，即使没有创建实例对象，只要类加载完成，就可以使用类属性了；
 *   6、类属性 的生命周期是随类的加载而创建，随着类消亡而销毁；
 *   7、静态属性初始化时，可以调用类中的 其他静态属性 或 调用静态方法 或 new一个新对象 为自己赋值，但不能使用非静态的属性或方法；
 *
 */
public class StaticAttribute {
    public static void main(String[] args) {
        StaticAttributeDemo tom = new StaticAttributeDemo("Tom");
        StaticAttributeDemo logon = new StaticAttributeDemo("logon");

        // 实例对象可以访问类属性，且多个实例访问的是同一个
        tom.count += 1;
        System.out.println(tom.count); // 1
        System.out.println(logon.count); // 1

        // 通过类名访问类属性
        StaticAttributeDemo.count += 2;
        System.out.println(StaticAttributeDemo.count); // 3

        // 实例再次访问，则已经是修改后的值
        System.out.println(tom.count); // 3
        System.out.println(logon.count); // 3

    }
}

class StaticAttributeDemo{
    // 实例属性
    private String name;

    // 类属性
    public static int count = 0;

    public StaticAttributeDemo(String name) {
        this.name = name;
    }
}