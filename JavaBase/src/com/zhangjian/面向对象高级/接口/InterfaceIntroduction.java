package com.zhangjian.面向对象高级.接口;

/**
 * 接口：
 *   接口就是给出一些没有实现的方法，封装到一起。当某个类要使用的时候，再根据实际情况来实现这些方法。
 *
 * 语法：
 *   interface 接口名{
 *       // final static 属性
 *       // abstract 方法
 *   }
 *
 * 说明：
 *   1、在 JDk7.0及之前的接口里面，所有方法都没有方法体，都是抽象方法
 *   2、在 JDK8.0 及之后的接口中，可以有 静态方法(static)、默认方法(default)，也就是说接口中可以有方法的具体实现
 *
 * 理解接口：
 *   1、接口可以理解为对Java单继承机制的一种补充
 *   2、对于公共的方法的统一管理，接口可以被多个类实现，多个类都接受接口的约束
 *   3、一个类实现了某个接口，那么接口中的所有 方法、属性 也都被实现的类继承走了。一个类实现一个接口，可以简单理解为继承了一个抽象类
 *
 * 使用细节：
 *   1、接口不能被实例化，可以定义在另一个类的内部
 *   2、接口中所有方法都是 public 方法(public 可以不写)，接口中的抽象方法可以不写 abstract，没有实现的方法都认为是 abstract 的
 *   3、接口中除了抽象方法，还可以有具有具体实现的 static方法 和 default方法，这两个 关键字修饰的方法有具体实现，实现类直接使用
 *   4、一个普通类实现接口，就必须将该接口中所有的抽象方法都实现
 *   5、抽象类实现接口，可以不用实现接口的抽象方法
 *   6、一个类可以同时实现多个接口。形式： class A implements Interface1,Interface2,...{}
 *   7、接口中的属性都是 静态常量，默认有 public final static 三个关键字修饰，且必须在定义时初始化。例如：int a = 1 就等价于 public final static int a = 1
 *   8、接口中属性的访问方式： 接口名.属性名。当然，实现了该接口的类，也可用 类名.属性名 或 类的实例名.属性名 来访问
 *   9、一个接口不能继承其他的类，但是可以继承多个其他的接口。形式：interface A extends B,C{}
 *   10、接口内抽象方法的修饰符只能是 public 和 默认，这和类的修饰符一致
 *   11、一个类实现了某个接口，就像继承了这个接口一样，同样具有 向上转型、向下转型 的多态特性
 */
public class InterfaceIntroduction {
    public static void main(String[] args) {
        // 接口可以直接访问接口中的属性
        System.out.println(Assemble.action);
        System.out.println(Assemble.description); // 访问父接口的属性
        System.out.println(AssembleHUAWEI.description); // 实现了接口的类也可以直接访问接口中的属性

        // 一个类实现了某个接口，也可以理解继承了这个接口，同样具有 向上转型、向下转型 的特点
        AssembleIPhone assembleIPhone = new AssembleIPhone();
        Assemble assembleHUAWEI = new AssembleHUAWEI(); // 向上转型 接口的多态

        test(assembleIPhone);
        test(assembleHUAWEI);

        // 接口版的向下转型
        ((AssembleHUAWEI) assembleHUAWEI).photo();
    }

    // 入参是 Assemble 接口类型，只要是实现了该接口的 类的实例 或 类的子类的实例，都可以传入。可以理解为接口版的向上转型
    public static void test(Assemble assemble){ // 向上转型 接口的多态
        assemble.startAssemble();
        assemble.isFinished();
    }
}

class AssembleHUAWEI implements Assemble, Interface{ // 一个类可同时实现多个接口
    @Override
    public void startAssemble() {
        System.out.println("开始组装HUAWEI手机");
    }

    @Override
    public boolean isFinished() {
        System.out.println("HUAWEI手机组装成功");
        return true;
    }

    public void photo(){
        System.out.println("华为手机拍照功能好");
    }
}

class AssembleIPhone implements Assemble{
    @Override
    public void startAssemble() {
        System.out.println("开始装苹果手机");
    }

    @Override
    public boolean isFinished() {
        System.out.println("苹果手机组装失败");
        return false;
    }
}

interface Assemble extends Interface{ // 接口可以继承其它接口
    // 属性
    int cost = 8; // 等价于  public final static int count = 8;
    public final static String action = "组装";

    // 抽象方法
    void startAssemble();

    boolean isFinished();

    // static方法
    static void introduction(){
        System.out.println("这是手机组装的接口");
    }

    // default方法
    default void declare(){
        System.out.println("本接口为富土康为指定客户提供，非客户厂商请勿实现");
    }
}

interface Interface{
    String description = "一个见名知义的接口而已";
}