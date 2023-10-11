package com.zhangjian.面向对象高级.接口;

/**
 * 继承和接口的爱恨情仇：
 *   1、接口和继承解决的问题不同
 *     继承的主要价值在于：解决代码的复用性和可维护性
 *     接口的主要价值在于：规范的设计，事先约定好方法的规范，让其他类去实现。更加灵活。
 *   2、按照编程规范，继承需要满足 is-a 的关系，而接口只需要满足 is-like 的关系
 *   3、接口在一定程度上实现代码的解耦
 *   4、当子类继承的父类和实现的接口中有相同的属性时，子类中使用该属性时，要显式的指定用谁的
 *
 * 通过下面的例子理解 继承和接口
 */
public class ExtendsVsImplements {
    public static void main(String[] args) {
        // 体验继承和接口的区别
        LittleMonkey monkey = new LittleMonkey("悟空");
        monkey.climb();
        monkey.fly();
        monkey.climb();

        // 处理继承和接口相同的属性
        monkey.showFootCount();
    }
}

// 小猴子通过继承获得猴子的技能；但是要获得飞翔和游泳的能力，就需要实现鸟儿和鱼的能力
// 这里体现的就是继承和实现的区别
class LittleMonkey extends Monkey implements BirdAbility, FishAbility{
    public LittleMonkey(String name) {
        super(name);
    }

    @Override
    public void fly() {
        System.out.println(getName() + "学会了飞翔");
    }

    @Override
    public void swim() {
        System.out.println(getName() + "学会了游泳");
    }

    public void showFootCount(){
        /*
        对于父类和接口中相同的属性，不能直接使用属性名称，jvm会分不清到底是用哪个
        因此需要我们显式的指定取谁的属性
         */
        // System.out.println(footCount); // ambiguous 模糊不清的

        System.out.println("猴子的脚有 " + super.footCount + " 只");
        System.out.println("鸟儿的脚有 " + BirdAbility.footCount + " 只");

    }
}

class Monkey{
    private String name;
    public int footCount = 4;

    public String Category = "猴";

    public String getName() {
        return name;
    }

    public Monkey(String name) {
        this.name = name;
    }

    public void climb(){ // 猴子的本能，攀爬
        System.out.println(name + "会爬树...");
    }
}

interface BirdAbility{
    int footCount = 2; // 等价于 public final static int footCount = 2;
    public abstract void fly(); // 接口中方法默认是 public abstract
}

interface FishAbility{
    void swim();
}