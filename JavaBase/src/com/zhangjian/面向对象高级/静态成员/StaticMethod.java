package com.zhangjian.面向对象高级.静态成员;

/**
 * 静态方法：
 *   静态方法也叫类方法。
 *
 * 语法：
 *   访问修饰符 static 返回类型 方法名(){}  [推荐]
 *   static 访问修饰符 返回类型 方法名(){}
 *
 * 静态方法的访问方式：
 *   类名.静态方法名  [推荐]
 *   实例名.静态方法名
 *
 * 静态方法的使用细节：
 *   1、当方法中不涉及到任何对象相关的成员，可以将方法设计成静态方法，提高开发效率，比如常用的 Math类、Arrays类、Collections类中的方法。
 *      在实际开发中也经常会有一些通用的、公共的方法类，就可以设计成静态方法；
 *   2、类方法 和 实例方法 都是随着类的加载而加载，并将结构信息存储在方法区；
 *   3、类方法 既可以通过 类名 调用，也可以通过 实例名 调用；实例方法 只能通过 实例名 调用；
 *   4、类方法 中没有隐含的 this(当前实例对象) 和 super(当前实例对象的父类对象) 参数；
 *   5、类方法 中只能访问 类属性和类方法（静态方法只能访问静态成员，可以 new新对象），不能访问 实例属性和实例方法；
 *   6、实例方法/非静态方法 既可以访问 静态成员，也可以访问 非静态成员；
 *   7、静态方法 和 属性(静态属性、非静态属性) 一样都没有多态特性，在哪个类中使用，就在哪个类中去找是否有申明，有就直接使用，没有在到父类中去找
 *     7.1、因为 静态成员(静态属性、静态方法) 都不依赖实例对象，而多态是对象的特征，因此也就能理解为什么静态成员不具有多态特性了
 *
 */
public class StaticMethod {
    public static void main(String[] args) {
        // 静态属性被多实例修改
        StaticMethodDemo tom = new StaticMethodDemo("tom");
        StaticMethodDemo jack = new StaticMethodDemo("jack");

        tom.payFee(567.33);
        jack.payFee(128.4);

        // 静态方法中可以访问其他静态成员
        StaticMethodDemo.showFee();

        System.out.print("\n\n\n");

        // 静态方法 和 属性 一样，不存在多态特性，在哪个类中使用，就在哪个类中去找是否有申明，有就直接使用，没有在到父类中去找
        Son son = new Son();
        son.showSecret(); // 子类实例调用父类的showSecret方法，虽然子类重写了 static secret，但仍然不会使用子类重写后的方法
        son.showSecretBySon(); // 子类实例调用自己的 showSecretBySon 方法，就使用自己的 secret 方法
        son.dance();
    }
}

class StaticMethodDemo{
    // 实例属性
    private String name;

    // 类属性
    public static double account = 0;

    public StaticMethodDemo(String name) {
        this.name = name;
    }

    // 实例方法/普通方法/非静态方法
    public void payFee(double fee){
        // 非静态方法 可以访问 静态和非静态的成员
        StaticMethodDemo.account += fee;
        System.out.println(name + " 支付了费用，一边 " + run() + " 一边 " + sing());
    }

    public String sing(){
        return "唱歌";
    }

    // 静态方法/类方法
    public static String run(){
        return "跑步";

        // 静态方法中不能访问非静态成员
//        sing();
//        System.out.println(name);

        // this.name;
        // super.xx
    }

    public static void showFee(){
        // 静态方法中只能访问静态成员
        System.out.println("账户余额: " + StaticMethodDemo.account);
        System.out.println("在静态方法中也可以 " + run());
    }
}

class Father{
    public static void secret(){
        System.out.println("父类中的 static secret...");
    }

    public void showSecret(){
        secret();
    }

    public static void dance(){
        System.out.println("父类中的 static dance...");
    }
}

class Son extends Father {
    // 重写父类的静态方法
    public static void secret() {
        System.out.println("子类中的 static secret...");
    }

    public void showSecretBySon() {
        secret();
    }

}