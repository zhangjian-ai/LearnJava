package com.zhangjian.面向对象基础.多态;

/**
 * 动态绑定机制：
 * 1、当对象调用方法时，该方法会和该对象的 运行类型 绑定，总是从 当前类/运行类型 中开始查找；
 * 2、当对象调用属性时，就不存在绑定机制。在哪里使用属性，就首先在使用属性的类里面查找，如果没有，再向上到父类中查找；
 */
public class PolymorphismDynamicBind {
    public static void main(String[] args) {
        // 向上转型
        Parent s1 = new Son1();
        System.out.println(s1.sum()); // 30 Son1没有sum方法，故到父类中找，执行sum时，要使用getI方法，Son1中有就不会使用父类的getI

        // 向上转型
        Parent s2 = new Son2();
        System.out.println(s2.sum1()); // 20 Son1没有sum方法，故到父类中找，执行sum时，要使用属性i，属性没有动态绑定，在sum方法所在的类中找，就是用的父类的属性i
    }
}

class Son2 extends Parent{
    public int i = 30;
}

class Son1 extends Parent{
    public int i = 20;

    @Override
    public int getI() {
        return i;
    }
}

class Parent{
    public int i = 10;

    public int getI() {
        return i;
    }

    public int sum(){
        return getI() + 10;
    }

    public int sum1(){
        return i + 10;
    }
}
