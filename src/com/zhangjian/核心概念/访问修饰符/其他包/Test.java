package com.zhangjian.核心概念.访问修饰符.其他包;

import com.zhangjian.核心概念.访问修饰符.VisitModifier;

public class Test {
    public static void main(String[] args) {
        VisitModifier visitModifier = new VisitModifier();

        // 其他包只能访问 public
        System.out.println(visitModifier.attr1);
        visitModifier.PublicFunc();

    }
}
