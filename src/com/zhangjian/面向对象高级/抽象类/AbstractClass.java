package com.zhangjian.面向对象高级.抽象类;

/**
 * 概念：
 *   1、用 abstract 关键字 修饰一个类时，这个类就是抽象类。抽象类中可以定义 抽象方法和普通成员方法。语法如下：
 *      访问修饰符 abstract class 类名{}
 *   2、用 abstract 关键字修饰一个方法，这个方法就是抽象方法，抽象方法没有 方法体。抽象方法只能定义在抽象类中，不能定义在普通类中。方法如下：
 *      访问修饰符 abstract 返回类型 方法名(参数列表);
 *   3、抽象类主要用于各种设计模式，设计好抽象类后，由子类来实现具体的业务逻辑。
 *
 * 使用细节：
 *   1、抽象类不能被实例化
 *   2、抽象类可以没有 abstract 抽象方法，可以只有 实现方法(普通成员方法)
 *   3、一旦类中包含了 abstract方法，则这个类必须申明为 abstract类
 *   4、abstract 只能修饰类和方法，不能修饰属性和其他内容
 *   5、抽象类可以有任意成员(抽象类的本质还是类)，比如：非抽象方法、构造器、静态成员等等
 *   6、抽象方法不能有主体，即不能实现
 *   7、如果一个类继承了抽象类，则它必须实现抽象类的 所有抽象方法 ，除非它自己也声明为 抽象类
 *   8、抽象方法不能使用 private、final、static 来修饰，因为这些关键字都是和 重写 互斥的，这些关键字修饰后的方法，子类无法重写
 *
 */
public class AbstractClass {
    public static void main(String[] args) {
        // 抽象方法的静态成员，同样可以直接使用
        System.out.println(AbstractFather.nickname);
        System.out.println(AbstractFather.salary);
        AbstractFather.sing();

        // 子类实例
        Son son = new Son("聂风");
        // 子类实现的父类抽象方法调用
        son.eat();
        System.out.println(son.songWords());

        // 子类调用父类final方法
        son.run();
    }
}

class Son extends AbstractFather{
    public Son(String name) {
        super(name);
    }

    @Override
    public void eat() {
        System.out.println("子类实现父类的 eat 抽象方法...");
    }

    @Override
    public String songWords() {
        return "风萧萧兮易水寒...";
    }
}

abstract class AbstractFather{
    // 抽象类可以有任何成员
    private String name;

    public static String nickname;

    public final static double salary = 1800.0;

    static {
        nickname = "雪饮狂刀";
        System.out.println("抽象父类静态代码块为 nickname 赋值...");
    }

    public AbstractFather(String name) {
        this.name = name;
    }

    public static void sing(){
        System.out.println("抽象父类的静态 sing 方法....");
    }

    public final void run(){
        System.out.println("抽象父类的 final run 方法...");
    }

    // 抽象方法，无方法体。需要子类实现
    public abstract void eat();

    public abstract String songWords();
}
