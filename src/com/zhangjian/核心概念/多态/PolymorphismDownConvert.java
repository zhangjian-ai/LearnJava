package com.zhangjian.核心概念.多态;

/**
 * 多态对象的向下转型：
 * 1、语法：子类类型 引用名 = (子类类型) 父类引用;
 * 2、只能强转 父类引用。就是说这里强转的一定是 编译类型是父类，运行类型是子类 的那个引用，不能把 运行类型也是父类的引用进行强转。
 * 3、不能强转父类的对象。就是说这里不能直接 (子类类型) new 父类();
 * 4、要求 父类引用 必须指向当前目标类型的对象。就是说当前 父类引用 指向的对象类型 就是要强转的 子类类型，而不能是该父类下其他的子类
 * 5、在进行向下转型后，引用 就可以调用子类中的所有成员了，包含子类的特有成员
 */
public class PolymorphismDownConvert {
    public static void main(String[] args) {
        // 向上转型
        // animal 的编译类型是 Animal02 ，运行类型是 Cat02
        Animal02 animal = new Cat02();

        // 此时不能访问子类的特有方法
        //animal.catchMouse();

        // 向下转型
        Cat02 cat = (Cat02) animal;  // animal 运行类型是 Cat02，则是可以向下转成 Cat02 类型的
        cat.catchMouse(); // 转型后，就可以访问子类特有方法了。打印 "Cat01 小猫在抓老鼠..."
        cat.run(); // 子类也实现了run方法，那么就直接使用自己的。 打印 "Cat01 小猫在跑..."
        cat.eat(); // 子类未实现的方法，向上到父类中去找。打印 "Animal01 动物在吃东西..."
        // animal.sleep() 父类私有方法不能访问
        // animal.name; 父类私有属性不能访问

        // 父类引用 指向的对象类型 就是要强转的 子类类型，而不能是该父类下其他的子类。将 animal 强转成Dog，编译是不会报错，但运行会有异常
        // java.lang.ClassCastException
//        Dog02 dog = (Dog02) animal;
    }
}


class Dog02 extends Animal02{}

class Cat02 extends Animal02 {
    private double weight;
    private int age;
    private String color;

    public void run(){
        System.out.println("Cat02 小猫在跑...");
    }

    public void catchMouse(){
        System.out.println("Cat02 小猫在抓老鼠...");
    }

}

class Animal02 {
    private String name;

    public void run(){
        System.out.println("Animal02 动物在跑...");
    }

    protected void eat(){
        System.out.println("Animal02 动物在吃东西...");
    }

    private void sleep(){
        System.out.println("Animal02 动物在睡觉...");
    }
}