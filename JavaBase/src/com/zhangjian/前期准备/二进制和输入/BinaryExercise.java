package com.zhangjian.前期准备.二进制和输入;

public class BinaryExercise {
    public static void main(String[] args) {
        //二进制以 0b 开头
        int binary = 0b1010;

        //十进制无前缀
        int decimal = 1010;

        //八进制以 0 开头
        int octal = 01010;

        //十六进制以 0x 或 0X 开头
        int hex = 0x1010;

        System.out.println(binary);
        System.out.println(decimal);
        System.out.println(octal);
        System.out.println(hex);

    }
}
