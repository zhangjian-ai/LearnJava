package com.zhangjian.异常.常见的运行时异常;

/**
 * ArithmeticException 算术异常
 *    当出现异常的运算条件时，抛出此异常
 */
public class ArithmeticException {
    public static void main(String[] args) {
        int a = 10;
        int b = 0;
        System.out.println(a / b);
    }
}
