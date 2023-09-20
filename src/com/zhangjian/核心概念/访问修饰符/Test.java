package com.zhangjian.核心概念.访问修饰符;

public class Test {
    public static void main(String[] args) {
        // 本类中可以访问所有权限的访问修饰符属性或方法
        // Test 和 visitModifier 在同一包中，无需导入
        VisitModifier visitModifier = new VisitModifier();
        visitModifier.demo();

        System.out.println("=====================");

        // Test 和 visitModifier 在同一包中，可以访问 public protected 默认修饰符
        VisitModifier visitModifier1 = new VisitModifier();
        System.out.println(visitModifier1.attr1 + " " + visitModifier1.attr2 + " " + visitModifier1.attr3);
        visitModifier1.PublicFunc();
        visitModifier1.ProtectedFunc();
        visitModifier1.DefaultFunc();

    }
}
