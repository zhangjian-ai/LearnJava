package com.zhangjian.前期准备.逻辑控制;

import java.util.Scanner;

public class WhileDetail {
    public static void main(String[] args) {
        /**
         * while 循环语法: while(express) { ... }
         * do-while 循环语法: do{ ... }while(express);
         *
         * 注意:
         * 1、循环条件 express 返回值一定是布尔类型；
         * 2、do-while 循环至少执行一次；
         * 3、do-while 循环最后有一个 分号；
         */

        // 输出 1-100 之间能被5整除，但不能被3整除的数
        int start = 1;
        int end = 100;
        while (start <= end){
            if (start % 5 == 0 && start % 3 != 0) {
                System.out.println(start);
            }
            start ++;
        }

        // 写一个循环，直到用户认怂
        char answer = ' '; // java 中不能有空串，至少得有一个空格符
        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("我帅吗？[y/n]");
            answer = scanner.next().charAt(0);

        }while (answer != 'y' );

    }
}
