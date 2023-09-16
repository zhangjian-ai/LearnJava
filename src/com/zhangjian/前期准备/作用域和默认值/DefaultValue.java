package com.zhangjian.前期准备.作用域和默认值;

public class DefaultValue {
    public static void main(String[] args) {
        Default aDefault = new Default();

        System.out.println(aDefault.num1); // 0
        System.out.println(aDefault.num2); // 0
        System.out.println(aDefault.num3); // 0
        System.out.println(aDefault.num4); // 0
        System.out.println(aDefault.aChar); // 空字符 '\u0000'
        System.out.println(aDefault.aBool); // false
        System.out.println(aDefault.num5); // 0.0
        System.out.println(aDefault.num6); // 0.0
        System.out.println(aDefault.aString); // null

    }
}

class Default{
    // 各基础数据类型的默认值
    byte num1;
    short num2;
    int num3;
    long num4;

    char aChar;
    boolean aBool;

    float num5;
    double num6;

    // String 类型
    String aString;

}
