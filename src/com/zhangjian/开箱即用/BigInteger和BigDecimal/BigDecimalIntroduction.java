package com.zhangjian.开箱即用.BigInteger和BigDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * BigDecimal 适合较大的浮点型数据的保存和运算
 */
public class BigDecimalIntroduction {
    public static void main(String[] args) {
        // 用法演示。不能直接 + - * / ，需要调用对应的方法
        BigDecimal bigDecimal = new BigDecimal("1998.46738487345738392321321");
        BigDecimal bigDecimal1 = new BigDecimal("3.51");

        System.out.println(bigDecimal.add(bigDecimal1)); // 加
        System.out.println(bigDecimal.subtract(bigDecimal1)); // 减
        System.out.println(bigDecimal.multiply(bigDecimal1)); // 乘

        // 除
        // 除法运算可能会得到一个无限循环的小数，此时程序抛出 ArithmeticException
        // 因此，一般使用 BigDecimal 另一个 divide 的重载方法
//        System.out.println(bigDecimal.divide(bigDecimal1));
        System.out.println(bigDecimal.divide(bigDecimal1, RoundingMode.CEILING)); // 返回值保留精度与分子一致
    }
}
