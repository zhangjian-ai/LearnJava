package com.zhangjian.面向对象基础.多态;

/**
 * 多态，即多种形态。
 *   在Java中，方法或对象具有多种形态，是面向对象的第三大特征。多态是建立在 继承 和 封装 的基础之上的。
 *
 * 方法的多态：
 *   方法的重载和重写体现了方法的多态。
 *
 * 对象的多态（核心）：
 *   1、理解两个概念。编译类型 和 运行类型 [ Animal animal = new Animal(); Animal cat = new Cat();]
 *     1.1、编译类型 是在申明一个变量时，就确定了，就是申明变量的类型。 上面 animal 和 cat 的编译类型就都是 Animal
 *     1.2、运行类型 是可以变化的，关键字 new 后面的类型就是 运行类型。上面两个变量运行类型 分别是 Animal 和 cat
 *   2、一个对象的 编译类型 和 运行类型 可以不一致
 */
public class PolymorphismIntroduction {
    public static void main(String[] args) {
        // 体验多态
        // animal 的编译类型是 Animal，运行类型是 Dog
        Animal animal = new Dog();
        animal.cry(); // 代码执行到本行时，animal 运行类型是Dog，因此此时调用的cry方法就是Dog类中的。打印 Dog 小狗汪汪叫...

        // animal 的编译类型仍然是 Animal，但运行类型变成了 Cat
        animal = new Cat();
        animal.cry(); // 代码执行到本行时，animal 运行类型是Cat，因此此时调用的cry方法就是Cat类中的。打印 Cat 小猫喵喵叫...

    }
}


class Dog extends Animal{
    public void cry(){
        System.out.println("Dog 小狗汪汪叫...");
    }
}

class Cat extends Animal{
    public void cry(){
        System.out.println("Cat 小猫喵喵叫...");
    }
}

class Animal{
    public void cry(){
        System.out.println("Animal 动物在叫...");
    }
}
