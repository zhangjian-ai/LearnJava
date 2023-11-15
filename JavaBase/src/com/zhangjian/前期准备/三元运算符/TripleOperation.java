package com.zhangjian.前期准备.三元运算符;

/**
 * 三元运算符
 *
 * 语法：
 *    逻辑表达式 ? 值1 : 值2
 *
 * 说明：
 *    当 逻辑表达式 返回值为 true 是，返回 值1，否则返回 值2
 *
 * 注意：
 *    三元运算符是一个整体，其中的两个返回值之间存在精度提升的问题。
 *    就是说 如果 值1 和 值2 精度类型不一样，三元运算符返回时，会以最高精度返回对应的值。(前提是两个返回值类型可以自动转换类型)
 *
 */
public class TripleOperation {
    public static void main(String[] args) {
        // 演示
        System.out.println( 2 > 1 ? "yes" : "no");
        System.out.println( false ? "yes" : "no");

        // 精度提升。出现两个可自动转换的类型时，总是返回较大精度的类型
        System.out.println( 2 > 1 ? 11 : 12.3); // 11.0 精度提升
        System.out.println( 2 > 1 ? new Integer(11) : new Double(12.3)); // 11.0 精度提升。包装类也一样

        // 不能互相转换的类型，互不影响
        System.out.println( 2 > 1 ? 11 : "物理"); // 11
        System.out.println( 2 < 1 ? 11 : "物理"); // 物理
    }
}
