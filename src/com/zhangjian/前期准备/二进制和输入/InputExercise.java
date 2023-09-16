package com.zhangjian.前期准备.二进制和输入;

import java.util.Scanner;

public class InputExercise {
    public static void main(String[] args){
        // 练习用户输入
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input your name: ");
        String name = scanner.nextLine();

        System.out.println("Please input your age: ");
        int age = scanner.nextInt();

        System.out.println("Please input your salary: ");
        Double salary = scanner.nextDouble();

        System.out.print("name = " + name + " age=" + age + " salary=" + salary.toString());
    }
}
