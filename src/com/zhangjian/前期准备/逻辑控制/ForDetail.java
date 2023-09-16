package com.zhangjian.前期准备.逻辑控制;

import java.lang.Math;
import java.util.InputMismatchException;

public class ForDetail {
    public static void main(String[] args) {
        /**
         * for循环细节：
         * 1、循环条件一定是返回布尔值的表达式；
         * 2、for(;express;)中变量的初始化和迭代可以写到其他地方，但是两个分号不能省略。express 也可以不写，则表示为一个无限循环；
         * 3、循环初始值可以有多条初始化语句，但要求类型一样，并且中间用逗号隔开。循环变量迭代也可以有多条变量迭代语句，中间用逗号隔开；
         */

        // 多个变量初始化迭代
        for(int a = 0, b = 0; a <= 3; a++, ++b){
            System.out.println("a=" + a + " b=" + b);
        }

        // 打印 1-100 之间所有 9 的倍数的整数，统计整数个数及总和
        int start = 1;
        int end = 100;
        int t = 9;
        int count = 0;
        int sum = 0;

        if (end <= start) {
            throw new InputMismatchException("起始值和结束值不正确");
        }

        for (;start <= end;){
            if (start % t == 0){
                System.out.println("当前值 " + start + " 是 " + t + " 的倍数" );
                count += 1;
                sum += start;
            }
            start++;
        }
        System.out.println(String.format("%s 倍数的个数及总和分别是%s %s", t, count, sum));


        /**
         * 打印空心金字塔：
         *     *
         *    * *
         *   *   *
         *  *     *
         * *********
         *
         * 分析：
         * 1、左侧空格数量 = 总层数 - 当前层数
         * 2、每一层需要打印的字符总数 包括 * 和 空格 数量 = 当前层数 * 2 - 1
         * 3、第一层和最后一层不打印空格
         * 4、其他层仅打印首位和末尾的 星号，其他打印 空格
         *
         */

        int totalLevel = 10;
        for (int i = 1; i <= totalLevel; i++){
            // 左侧空格
            for (int k = 1; k <= (totalLevel - i); k++){
                System.out.print(" ");
            }

            // 字符总数
            for (int h = 1; h <= 2 * i - 1; h ++){
                // 首行及尾行不打印空格
                if (i == 1 || i == totalLevel){
                    System.out.print("*");
                    continue;
                }
                // 其他行打印 星号 和 空格
                // 第一个星号 和 最后一个星号
                if (h == 1 || h == 2 * i - 1) {
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }

            // 换行
            System.out.println("");
        }

        int a = (int)(Math.random() * 100);
        System.out.println(a);
    }
}
