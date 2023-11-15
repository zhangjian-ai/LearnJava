package com.zhangjian.面向对象高级.final关键字;

/**
 * final 关键字：
 *    final 可以修饰 类、属性、方法 和 局部变量(代码块中定义的变量、方法形参)
 *
 *    final 修饰类时，这个类将不能被继承；
 *    final 修饰方法时，这个方法将不能被子类重写；
 *    final 修饰属性时，这个属性的值将不能被修改，这样的属性也称为 常量；
 *    final 修饰局部变量时，这个局部变量将不能被修改，这样的局部变量也称为 局部常量；
 *
 * final 使用细节：
 *   1、final 修饰的属性又叫常量，常量的变量名 一般由 大写字母和下划线 组成，例如：MONEY_RATE
 *      1.1、字符串常量是存放在常量池中的，且一旦创建，将不能修改。
 *      1.2、final 修饰的变量，如果指向的是一个对象的地址，那么该变量指向的地址不可修改，而对象本身是可以修改的
 *   2、final 修饰的属性，必须赋初值，并且之后不能再做修改。在如下三个位置完成赋值都可以：
 *     2.1、在 常量定义时，直接赋值。如： public final double MONEY_RATE = 0.05;
 *     2.2、在 构造器中
 *     2.3、在 代码块中
 *   3、final 修饰静态属性，那么静态属性也必须赋初值，但只能在如下两个位置赋值：
 *     3.1、在 静态常量定义是，直接赋值。如：public final static double MONEY_RATE = 0.05;
 *     3.2、在 静态代码块中
 *   4、final 修饰的类不能被继承，但可以被实例化
 *   5、如果类不是final类，但其中包含final方法，则该final方法不能被子类重写，但该final方法可以被子类继承并使用
 *   6、一般来说，如果一个类已经是final类了，就没有必要再将方法修饰成final方法了
 *   7、final 不能修饰构造器
 *   8、final 和 static 同时修饰的属性，且该属性在定义时就被赋值，那么该属性在被 类名 直接调用时，将不会触发类加载(编译阶段就将字段信息添加到了方法区)，通常定义常量都是二者一起使用 [很重要]
 *   9、包装类(Integer、Double、Float、Boolean)和String类也都是 final类
 *
 */
public class Final {
    public static void main(String[] args) {
        D d = new D();
        System.out.println(d.money);  // 静态属性可以被继承
        System.out.println(d.name);
        d.sing(); // 静态方法可以被继承


        // 调用 final static 修饰的属性，不会触发类加载，因此不会执行静态代码块
        System.out.println("##################");
        System.out.println(FinalStaticDemo.name);

        System.out.println("##################");
        System.out.println(FinalStaticDemo.nickname); // nickname 在 static 代码块中赋值，那一定会加载类

    }
}


final class A{}

// final 类将不允许被继承
// class B extends A{}

class C {
    public final double money;

    public C() {
        // 在构造器中初始化
        this.money = 22.8;
    }

    public final void sing(){
        System.out.println("阿狗在快乐的歌唱...");
    }
}

class D extends C {
    public final String name;

    {
        name = "倩倩"; // 在代码块中赋值
    }

    // 父类的静态方法不允许子类进行重写
    // public void sing(){}
}

class FinalStaticDemo{
    // 直接在定义时，为常量赋值。那么在 静态代码块 中将不能再为其赋值
    public final static String name = "二狗";

    public final static String nickname;

    public static String sign = "生亦何欢，死亦何苦";

    static {
        // name = "阿朱";  // name 定义时已被赋值，这里将不再允许为其赋值
        nickname = "游戏人生"; // 定义时没赋值，那就要在静态代码块中赋值，否则会报错，编译不会通过
        System.out.println("静态代码块执行...");
    }

}
