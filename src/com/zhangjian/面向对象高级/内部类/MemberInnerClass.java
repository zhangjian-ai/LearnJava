package com.zhangjian.面向对象高级.内部类;

/**
 * 成员内部类：
 *    成员内部类 是定义在外部类的成员位置上的，是外部类诸多成员中的一个，并且没有static修饰
 *
 * 说明：
 *    1、成员内部类 可以 直接访问 外部类的所有成员，包含私有的
 *    2、可以添加任意 访问修饰符，因为它就是一个成员
 *    3、成员内部类的作用域和外部类的其他成员一样，是整个类体
 *    4、外部类 通过 创建对象的方式 访问 成员内部类的成员
 *    5、外部其他类可以访问成员内部类，在外部其他类中，可以用一下两种方式访问
 *       5.1、在外部其他类直接创建对象，语法： 外部类名.成员内部类名 对象名 = 外部类对象.new 成员内部类名(参数列表);
 *       5.2、在外部类中提供一个获取 成员内部类实例 的方法 [推荐]
 *    6、如果外部类和内部类的成员重名时，在内部类中访问默认遵循就近原则。如果要访问外部类的同名成员，则使用 外部类名.this.成员名 的方式访问
 */
public class MemberInnerClass {
    public static void main(String[] args) {
        // 创建一个外部类对象
        MemberOuterCLass outerCLass = new MemberOuterCLass();
        System.out.println("非常正常的访问外部类的非私有属性age = " + outerCLass.age);
        outerCLass.callInnerClass();

        // 其他类访问内部类方式一
        // 语法： 外部类名.成员内部类名 对象名 = 外部类对象.new 成员内部类名(参数列表);
        // 这里之所以语法有点复杂，是因为内部类是非静态的，需要通过对象来访问成员
        MemberOuterCLass.MemberInnerCLass innerCLass = outerCLass.new MemberInnerCLass(23, '女');
        innerCLass.innerSay();

        // 其他类访问内部类方式二
        // 使用外部类提供的方法
        MemberOuterCLass.MemberInnerCLass memberInnerCLassInstance = outerCLass.getMemberInnerCLassInstance(22, '女');
        memberInnerCLassInstance.innerSay();

        // 内部类如果是私有的，那么其他类就不能访问了
        // MemberOuterCLass.MemberInnerCLass02

    }
}

class MemberOuterCLass{
    private String name = "外部类";
    public int age = 28;

    private void say(){
        System.out.println("外部类在说话");
    }

    class MemberInnerCLass{
        private int age;
        private char gender;

        public MemberInnerCLass(int age, char gender) {
            this.age = age;
            this.gender = gender;
        }

        public void innerSay(){
            // 同名属性，遵循就近原则
            // 通过 外部类名.this.成员名 直接访问外部类属性
            System.out.println(age);
            System.out.println(MemberOuterCLass.this.age);

            // 内部类可直接访问外部类成员，包括私有
            System.out.println(name);
            say();
        }
    }

    private class MemberInnerCLass02{}

    public void callInnerClass(){
        System.out.println("外部类访问内部类开始");

        // 外部类访问内部类成员，需要通过创建对象的方式
        MemberInnerCLass memberInnerCLass = new MemberInnerCLass(18, '男');
        System.out.println(memberInnerCLass.gender); // 外部类和内部类此时都属于同一个类，因此所有私有属性也都能访问
        memberInnerCLass.innerSay();

        System.out.println("外部类访问内部类结束");
    }

    // 提供一个返回对象的方法
    public MemberInnerCLass getMemberInnerCLassInstance(int age, char gender){
        return new MemberInnerCLass(age, gender);
    }

}
