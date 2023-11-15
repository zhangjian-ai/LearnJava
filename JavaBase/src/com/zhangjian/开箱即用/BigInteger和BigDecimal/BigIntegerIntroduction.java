package com.zhangjian.开箱即用.BigInteger和BigDecimal;

import java.math.BigInteger;

/**
 * BigInteger 适合比较大的整形数据的保存和运算
 */
public class BigIntegerIntroduction {
    public static void main(String[] args) {
        // 用法演示。不能直接 + - * / ，需要调用对应的方法
        BigInteger bigInteger = new BigInteger("4783947386473549308274833029321321");
        BigInteger bigInteger1 = new BigInteger("4569827732");

        System.out.println(bigInteger.add(bigInteger1)); // 加
        System.out.println(bigInteger.subtract(bigInteger1)); // 减
        System.out.println(bigInteger.multiply(bigInteger1)); // 乘
        System.out.println(bigInteger.divide(bigInteger1)); // 除
    }
}
