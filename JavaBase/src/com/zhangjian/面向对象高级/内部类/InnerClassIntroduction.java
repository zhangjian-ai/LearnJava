package com.zhangjian.面向对象高级.内部类;

/**
 * 类的五大成员：
 *   属性、方法、构造器、代码块、内部类
 *
 * 内部类：
 *   在一个类的内部，又完整的嵌套了另一个类结构。
 *   这个被嵌套的类称之为 内部类(inner class)，嵌套其他类的类称为 外部类(outer class)。
 *   内部类是 类 的第五大成员。
 *   内部类最大的特点就是可以直接访问外部类的所有成员(包括私有)，因为内部类本身就是外部类的一部分。
 *
 * 语法：
 *   class outer{ // 外部类
 *       inner class{ // 内部类
 *       }
 *   }
 *
 *   class other{ // 外部其他类
 *   }
 *
 * 内部类的分类：
 *   a、定义在外部类的局部位置上的有：
 *     1、局部内部类(有类名)
 *     2、匿名内部类(无类名)
 *
 *   b、定义在外部类的成员位置上的有：
 *     1、成员内部类(没有static)
 *     2、静态内部类(有static)
 */
public class InnerClassIntroduction {
    // 类的五大成员

    private String name = "Jack";

    public void sing(){};

    public InnerClassIntroduction(String name) {
        this.name = name;
    }

    {
        System.out.println("code block");
    }

    class innerClass{}
}
