package com.zhangjian.核心概念.成员方法;

public class FunctionOverLoad {
    public static void main(String[] args) {
        /**
         * 方法重载注意事项和使用细节：
         * 1、方法名：必须相同
         * 2、参数列表：必须不同（参数的 类型、个数、顺序 至少有一个不同，参数名称无要求）
         * 3、返回值类型 无要求
         */

        Methods methods = new Methods();

        // 根据参数个数或类型，分别调用重载的方法
        System.out.println(methods.max(10, 20)); // 20
        System.out.println(methods.max(111.1, 121.1)); // 121.1
        System.out.println(methods.max(33.2, 55.1, 66.3)); // 66.3

        // 重载方法中，也可以自动类型转换。这里的 10 在调用方法时自动转换成 double
        // 如果有max方法类型是 int double double ，那么将优先匹配数据类型完全匹配的方法。
        System.out.println(methods.max(10, 9.9, 8.8)); // 10.0

    }
}


class Methods{
    /**
     * 在Methods类中，定时三个重载方法。
     * 第一个方法，返回两个int值中的最大值；
     * 第二个方法，返回两个double值中的最大值；
     * 第三个方法，返回三个double之中的最大值；
     */

    public int max(int num1, int num2){
        return num1 > num2 ? num1 : num2;
    }

    public double max(double num1, double num2){
        return num1 > num2 ? num1 : num2;
    }

    public double max(double num1, double num2, double num3){
        double temp = num1 > num2 ? num1 : num2;
        return temp > num3 ? temp : num3;
    }
}