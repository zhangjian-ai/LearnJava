package com.zhangjian.核心概念.继承.测试子类;

import com.zhangjian.核心概念.继承.Extends;
import com.zhangjian.核心概念.继承.ExtendsConstructor;

public class TestExtends {
    public static void main(String[] args) {
        // 细节一
        TestDetail testDetail = new TestDetail();
        testDetail.detail01();

        System.out.println();

        // 细节二
        TestConstructor testConstructor = new TestConstructor();
        TestConstructor jack = new TestConstructor("Jack");
        TestConstructor john = new TestConstructor("John", 22);

    }

}

class TestDetail extends Extends{
    public void detail01(){
        // 1、子类会继承父类所有的 属性 和 方法，但访问时要受访问修饰符控制只能访问 public 和 protected 两种级别的属性和方法
        System.out.println(attr1 + " " + super.attr2);  // attr3 和 attr4 只能通过 public getXXX 获得

        publicFunc();
        protectedFunc();

        // 通过public再次对外暴露默认级别和私有级别的方法
        // callDefaultFunc();
        // callPrivateFunc();

    }
}



class TestConstructor extends ExtendsConstructor{
    public TestConstructor() {
        // 子类中默认就带有 super() 因此写于不写都会调用父类的无参构造器
        // super();
        System.out.println("子类无参构造器...");
    }

    public TestConstructor(String name) {
        // 子类构造器有无参数，与调用父类有无参数的构造器无关
        // 这里调用父类默认构造器，也可以不写
        super();
        System.out.println("子类的有参构造器...");
    }

    public TestConstructor(String name, int age){
        // 参数我们可以随意使用
        // 这里调用父类有参构造器，必须显式指定
        super(name);
        System.out.println("子类的有参构造器，只使用一个参数作为父类的构造器参数");
    }
}

