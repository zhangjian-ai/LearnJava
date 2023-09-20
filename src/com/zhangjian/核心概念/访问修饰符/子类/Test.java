package com.zhangjian.核心概念.访问修饰符.子类;

import com.zhangjian.核心概念.访问修饰符.VisitModifier;

public class Test extends VisitModifier {
    public static void main(String[] args) {
        // 这个Test就是当前类，
        Test test = new Test();

        // 子类中可以访问 public protected
        System.out.println(test.attr1 + " " + test.attr2);

        test.PublicFunc();
        test.ProtectedFunc();
    }
}
