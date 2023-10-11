package com.zhangjian.面向对象高级.内部类;

/**
 * 静态内部类：
 *    静态内部类 是定义在外部类的成员位置上的，是外部类诸多静态成员中的一个，用static修饰
 *
 * 说明：
 *    1、静态内部类 只能直接访问 外部类的所有静态成员(包含私有的)，不能访问非静态成员。因为 静态内部类 和其他静态成员一样，都是跟随类加载而创建，不依赖实例
 *    2、可以添加任意 访问修饰符，因为它就是一个成员
 *    3、作用域和外部类的其他成员一样，是整个类体
 *    4、外部类 通过 创建对象 来访问 静态内部类的成员，如果是 内部类的静态成员，则可以世界使用 内部类名.成员名
 *    5、外部其他类可以访问成员内部类，在外部其他类中，可以用一下两种方式访问
 *       5.1、在外部其他类直接创建对象，语法： 外部类名.静态内部类名 对象名 = new 外部类对象.静态内部类名(参数列表);
 *       5.2、在外部类中提供一个获取 静态内部类实例 的方法，由于是静态的内部类，那就可以对外提供静态的方法获取内部类实例 [推荐]
 *    6、如果外部类和内部类的成员重名时，在内部类中访问默认遵循就近原则。如果要访问外部类的同名成员，则使用 外部类名.成员名 的方式访问，
 *       因为只能访问外部类的静态成员，就没必要使用 this
 */
public class StaticInnerClass {
    public static void main(String[] args) {
        // 外部类访问内部类
        new StaticOutClass().OuterCallInner();

        // 其他类访问内部类方式一
        StaticOutClass.InnerClass innerClass = new StaticOutClass.InnerClass(24);
        innerClass.compare();

        // 其他类访问内部类方式二
        StaticOutClass.getInnerClassInstance(23).compare();


    }
}


class StaticOutClass{
    private String name = "外部类哦";
    private static String desc = "一个标致的外部类";
    private static String nickname = "花开富贵呢";

    private static void say(){
        System.out.println("外部类想说点什么");
    }

    // 静态内部类可以定义自己的非静态成员，但不能访问外部类的非静态成员
    static class InnerClass{
        private int age;
        private static String nickname = "内部类花开富贵呢";

        public InnerClass(int age) {
            this.age = age;
        }

        public void compare(){
            // 直接访问外部类静态属性
            System.out.println(desc);

            // 同名属性就近原则
            System.out.println(nickname);

            // 访问外部类的同名属性。因为是静态成员，直接 类名.成员名
            System.out.println(StaticOutClass.nickname);
            StaticOutClass.say();
        }
    }

    // 外部类访问内部类
    public void OuterCallInner(){
        // 内部类的静态成员，同样用类名直接访问
        System.out.println(InnerClass.nickname);

        // 非静态的则需要创建实例
        System.out.println(new InnerClass(22).age);
    }

    // 这里演示一个静态的提供实例的方法，当然也可以是非静态的
    public static InnerClass getInnerClassInstance(int age){
        System.out.println("对外直接获取静态内部类实例的方法");
        return new InnerClass(age);
    }
}
