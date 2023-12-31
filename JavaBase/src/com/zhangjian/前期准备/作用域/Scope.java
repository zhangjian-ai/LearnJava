package com.zhangjian.前期准备.作用域;

public class Scope {
    public static void main(String[] args) {
        /**
         * 背景：
         * 1、在java编程中，主要的变量就是属性（成员变量）和局部变量；
         * 2、局部变量一般是指在成员方法中定义的变量；
         *
         * Java中作用域的分类：
         * 全局变量：也就是属性，直接在类中定义，作用域为整个类，成员方法可以直接使用；
         * 局部变量：就是除了属性之外的其他变量，作用域为定义它的代码块。一般是在成员方法内部定义并使用，在其他成员方法则不能直接使用
         *
         *
         * 使用细节：
         * 1、全局变量可以不赋值而直接使用，因为java会给其一个对应类型默认值。局部变量则必须赋值后才能使用，它是没有默认值的；
         * 2、属性和局部变量可以重名，访问时遵循就近原则；
         * 3、在同一个作用域中，两个变量不能重名。即 两个全局变量 或 成员方法中的两个变量 都不能重名；
         * 4、属性生命周期较长，伴随对象创建而创建、对象销毁而销毁；局部变量生命周期短，伴随它的代码块的执行而创建，代码块之行结束则销毁；
         * 5、全局变量可以被本类使用或其他类通过对象调用，局部变量只能在本类对应的方法中使用；
         * 6、修饰符不同。全局变量/属性可以加修饰符，局部变量不可以加修饰符
         */
    }
}
