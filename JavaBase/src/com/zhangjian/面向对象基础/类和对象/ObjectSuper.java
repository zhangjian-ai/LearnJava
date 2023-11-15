package com.zhangjian.面向对象基础.类和对象;

public class ObjectSuper {
    public static void main(String[] args) {
        B b = new B();
        b.visitSuper();

        System.out.println(">>>>>><<<<<<<");
        b.callEat();
        b.callSing();



    }
}


/**
 * super 代表父类的引用，用于访问父类的 属性、方法、构造器
 *
 * 使用细节：
 * 1、子类可以访问父类的 属性、方法，但只能访问 public 和 protected 两种级别，如果子类和父类在同一包中，则还可以访问默认级别
 * 2、访问父类构造器，只能在子类的构造器中访问，且必须在构造器第一行，形式：super(参数列表)。
 * 3、子类访问 属性/方法 时（不使用super），则从当前类向上一级一级的找，在某一级找到 属性/方法 时，还要检查 访问权限，有权限则访问，没有权限则报错，不会再向后找
 * 4、当子类中有和父类中的成员（属性或方法）重名时，为了访问父类的成员。必须通过 super
 * 5、super 的访问不限于直接父类，如果爷爷类和本类中有同名成员，也可以使用super去防蚊液也累的成员；如果多个基类中都有同名的成员，使用super访问时遵循就近原则，
 *    同时也需要遵守访问权限的规则
 */
class B extends A{
    // 调用父类默认构造函数
    public B() {
        super();
    }

    public void visitSuper(){
        // 属性
        System.out.println(super.attr1 + " " + super.attr2 + " " + super.attr3);

        // 方法
        super.publicFunc();
        super.protectedFunc();
        super.defaultFunc();

        // 这里父类子类在同一包中，因此可以访问默认级别的属性和方法
        // 但仍然不能访问私有属性及方法
//        System.out.println(super.attr4);
//        super.privateFunc();
    }

    public void eat(){
        System.out.println("B类的 eat 方法...");
    }

    public void callEat(){
        // eat 方法父类和子类都有，前两种方式则访问的是本类的eat，后一种 访问的是 父类的
        eat();
        this.eat();

        super.eat(); // 使用super时，将不会在本类中查找属性/方法，而是直接到父类中查找
    }

    public void callSing(){
        // sing 方法只有父类有，因此下面三种方式效果相同
        sing();
        this.sing();
        super.sing();
    }

}


class A{
    public String attr1 = "PublicAttr";
    protected String attr2 = "ProtectedAttr";
    String attr3 = "DefaultAttr";
    private String attr4 = "PrivateAttr";

    public void publicFunc(){
        System.out.println("publicFunc");
    }

    protected void protectedFunc(){
        System.out.println("protectedFunc");
    }

    void defaultFunc(){
        System.out.println("defaultFunc");
    }

    private void privateFunc(){
        System.out.println("privateFunc");
    }

    public void eat(){
        System.out.println("A类的 eat 方法...");
    }

    public void sing(){
        System.out.println("A类的 sing 方法");
    }

}