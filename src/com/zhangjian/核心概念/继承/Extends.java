package com.zhangjian.核心概念.继承;

/**
 * 继承：
 *  继承可以解决重复代码的问题，让我们的编程更加靠近人类思维。当多个类存在相同的 属性(变量) 和 方法 时，可以从这些类中抽象出父类，
 *  在父类中定义这些相同的属性和方法，所有的子类不需要重新定义这些属性和方法，只需要通过关键字 extends 来申明继承父类即可。
 *
 * 继承的语法：
 *  class 子类名 extends 父类{}
 *
 *  1、子类会自动拥有父类定义的 属性 和 方法
 *  2、父类又叫 超类、基类
 *  3、子类又叫 派生类
 *
 * 继承细节(在 子类.TestExtends 中用不同的方法演示每个细节)：
 *  1、子类会继承父类所有的 属性 和 方法，但访问时要受访问修饰符控制只能访问 public 和 protected 两种级别的属性和方法
 *  2、子类构造器中必须首先调用父类的构造器，完成父类的初始化，形式 super() [演示 ExtendsConstructor]
 *     2.1、在子类的构造器中，java会给子类每个构造器默认加上这一句，不用显式编写，当然前提是父类有默认构造器
 *     2.2、如果父类没有默认构造器，那么必须在子类的构造器中使用 super(参数列表) 指定父类具体的构造器完成对父类的初始化
 *     2.3、super() 只能在构造器中使用，不能在成员方法中使用，且super语句必须在构造器的第一行
 *     2.4、super() 和 this() 都只能放在构造器的第一行，因此这两个关键字不能共存在一个构造器
 *     2.5、对 super 的调用[super()]只能在构造器中，但 super 关键字可以在成员方法中使用， 通过 super.属性 / super.方法 来访问父类中的属性或方法
 *  3、java中所有类都是 Object类 的子类；
 *  4、父类构造器的调用，不限于直接父类，将一直向上追溯直到 Object类。[在父类的构造器中同样有隐藏的 super()]
 *  5、子类最多只能直接继承一个父类，在java中是 单继承 机制
 *  6、当存在多级继承时，子类访问 属性/方法 时，从当前类向上一级一级的找，在某一级找到 属性/方法 时，还要检查 访问权限，有权限则访问，没有权限则报错，不会再向后找
 *  7、不能滥用继承，子类和父类必须满足 is-a 的关系。比如：Cat is a Animal 则 Cat类 继承 Animal 类是合理的
 *
 */
public class Extends { // 光标停留在类名上时，使用 ctrl + h 可以查看类的继承关系
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

    // 为默认级别和private级别属性增加get和set方法
    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    // 为默认级别和private级别属性增加public访问入口
    public void callDefaultFunc(){
        defaultFunc();
    }

    public void callPrivateFunc(){
        privateFunc();
    }

}


