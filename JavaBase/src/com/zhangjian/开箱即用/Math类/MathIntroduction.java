package com.zhangjian.开箱即用.Math类;

/**
 * Math 类包含用于数学运算的基本方法，是一个标准的工具类
 */
public class MathIntroduction {
    public static void main(String[] args) {
        // 演示一些常用方法

        System.out.println(Math.abs(-101)); // 101 求绝对值

        System.out.println(Math.pow(3, 3.4)); // 41.9 求幂 返回类型是 double 3的3.4 次方

        System.out.println(Math.ceil(5.55)); // 6.0 向上取整 返回类型是 double

        System.out.println(Math.floor(6.77)); // 6.0 向下取整 返回类型是 double

        System.out.println(Math.round(7.88d)); // 8  四舍五入 返回类型 int/long 根据输入决定 float/double

        System.out.println(Math.sqrt(9)); // 3.0 开方 返回类型是 double

        System.out.println(Math.max(10, 13)); // 13 求最大值 返回类型与输入类型一致

        System.out.println(Math.min(13.1, 66.7)); // 13.1 求最小值  返回类型与输入类型一致

        // 获取一个 0 ~ 1 之间的随机小数。返回类型是 double
        System.out.println(Math.random());

        // 获取 a ~ b 之间的一个随机整数：(int)(a + Math.random() * (b - a + 1))
        // 获取 1 - 100 之间的随机整数
        for (int i = 0; i < 100; i++) {
            System.out.print((int)(1 + Math.random() * (100 - 1 + 1)));
            System.out.print(" ");
        }

    }
}
