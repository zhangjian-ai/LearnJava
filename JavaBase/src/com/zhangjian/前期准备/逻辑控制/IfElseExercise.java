package com.zhangjian.前期准备.逻辑控制;

import java.util.Scanner;

public class IfElseExercise {
    public static void main(String[] args) {
        // 根据输入的分数判断信用
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入你的信用分");
        int score = scanner.nextInt();

        if(score >= 100) {
            System.out.println("nice boy");
        }
        else if(score > 80 && score <= 99) {
            System.out.println("good boy");
        }
        else if(score > 60) {
            System.out.println("bad boy");
        }
        else {
            System.out.println("fuck you boy");
        }
    }
}
