package com.zhangjian.面向对象高级.内部类;

/**
 * 局部内部类：
 *    局部内部类是指定义在外部类的局部位置，比如 方法体、代码块 中，并且有类名
 *
 * 说明：
 *   1、局部内部类可以直接访问外部类的所有成员，包含私有的
 *   2、局部内部类不能添加访问修饰符，因为他本质也就是个局部变量。但是可以使用 final 修饰，其他局部成员也都是可以使用 final 修饰的
 *   3、局部内部类作用域仅仅在定义它的代码块中
 *   4、外部类访问局部内部类的成员，访问方式：创建对象 -> 访问成员
 *   5、外部其他类不能访问局部内部类，还是因为 局部内部类 是一个局部变量
 *   6、如果外部类和局部内部类的成员重名时，默认遵循就近原则。如果想访问外部类的成员，则可以使用 外部类名.this.成员名 进行访问
 *
 */
public class PartInnerClass {
    public static void main(String[] args) {
        OuterClass01 class01 = new OuterClass01("悟净");
        class01.showMe();
    }
}

class OuterClass01{
    private String name;
    private int age = 22;

    public OuterClass01(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("外部类的 show ...");
    }

    public void showMe(){
        String desc = "局部desc";

        final class ClassInShowMe{
            private String attr = "内部类属性";
            public final static String attr2 = "内部类静态属性";
            int age = 18;

            public void m1(){
                // 直接访问外部类成员
                System.out.println(name);
                show();

                //同名的成员遵循就近原则
                System.out.println(age);
                System.out.println(OuterClass01.this.age); // 访问外部类的age
            }
        }

        // 在外部类访问内部类
        System.out.println(ClassInShowMe.attr2); // 静态成员直接访问

        // 非静态的成员通过实例访问
        ClassInShowMe me = new ClassInShowMe();
        System.out.println(me.attr);
        me.m1();
    }
}
