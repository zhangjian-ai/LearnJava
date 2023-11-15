package com.zhangjian.面向对象基础.多态;

/**
 * 对象多态的向上转型
 *   1、本质：父类的引用指向了子类的对象
 *   2、语法：父类类型 引用名 = new 子类类型();
 *   3、特点：
 *     3.1、可以调用父类中所有成员（遵守访问权限），不能调用子类特有成员。一个对象在创建时，可以调用哪些成员，是由 编译类型 决定的，
 *          因此子类有但父类没有的成员，是不能访问的；
 *     3.2、最终运行效果看子类的具体实现。这里和继承的特性一致，即调用的成员时，从子类开始向上查找，找到了就调用执行。[本条仅适用于成员方法]
 */
public class PolymorphismUpConvert {
    public static void main(String[] args) {
        // 向上转型
        Animal01 animal = new Cat01();
        Object object = new Cat01(); // 这个也是可以的，Object 是最上层的基类

        animal.run(); // 子类也实现了run方法，那么就直接使用自己的。 打印 "Cat01 小猫在跑..."
        // animal.catchMouse(); 子类特有方法不能访问
        animal.eat(); // 子类未实现的方法，向上到父类中去找。打印 "Animal01 动物在吃东西..."
        // animal.sleep() 父类私有方法不能访问
        // animal.name; 父类私有属性不能访问
    }
}

class Cat01 extends Animal01 {
    private double weight;
    private int age;
    private String color;

    public void run(){
        System.out.println("Cat01 小猫在跑...");
    }

    public void catchMouse(){
        System.out.println("Cat01 小猫在抓老鼠...");
    }

}

class Animal01 {
    private String name;

    public void run(){
        System.out.println("Animal01 动物在跑...");
    }

    protected void eat(){
        System.out.println("Animal01 动物在吃东西...");
    }

    private void sleep(){
        System.out.println("Animal01 动物在睡觉...");
    }
}
