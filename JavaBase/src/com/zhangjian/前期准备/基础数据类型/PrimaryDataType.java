package com.zhangjian.前期准备.基础数据类型;

/**
 * 基础数据类型
 *    Java 中有 8 种基础数据类型，信息如下：
 *
 *      分类          名称          内存空间(byte 字节)
 *      整数类型        byte        1
 *      整数类型        short       2
 *      整数类型        int         4
 *      整数类型        long        8
 *      浮点类型        float       4
 *      浮点类型        double      8
 *      字符型         char        2
 *      布尔型         boolean     1
 *
 * 基础数据类型作为属性时，jvm会为其分配默认值。
 */
public class PrimaryDataType {
    public static void main(String[] args) {
        Default aDefault = new Default();
        System.out.println(aDefault.num1); // 0
        System.out.println(aDefault.num2); // 0
        System.out.println(aDefault.num3); // 0
        System.out.println(aDefault.num4); // 0
        System.out.println(aDefault.aChar); // \u0000
        System.out.println(aDefault.aBool); // false
        System.out.println(aDefault.num5); // 0.0
        System.out.println(aDefault.num6); // 0.0
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

    // 字符不能为空字符
    //char a = '';

    // 字符串可以为空字符串
    String b = "";

}