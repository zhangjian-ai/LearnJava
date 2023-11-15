package com.zhangjian.面向对象高级.枚举类型;

import java.lang.annotation.Repeatable;

/**
 * 使用 enum 关键字创建枚举类：
 *    1、当使用 enum关键字 时，枚举类会默认继承 Enum类(也意味着当前枚举类不能再显式继承别的类，但可以实现接口)
 *    2、当使用 enum关键字 时，当前枚举类默认是 final 类，不可其他子类被继承
 *    3、使用 enum 后，枚举对象 的定义简化成 对象名(实参列表)，实参列表 取决于具体的构造器
 *    4、如果枚举类使用无参构造器 创建 枚举对象，则实参列表和小括号都可以省略
 *    5、当有多个枚举对象时，使用 , 间隔，最后以 ; 结尾
 *    6、枚举对象 必须在 枚举类 类体的首部定义
 */
public class UseEnumKeyword {
    public static void main(String[] args) {
        System.out.println(EnumSeason.SPRING);
        System.out.println(EnumSeason.AUTUMN.getName()); // 枚举对象调用方法，和正常对象使用方法无异
        System.out.println(EnumSeason.WINTER);
        System.out.println(EnumSeason.SUMMER.getDesc());
    }
}

enum EnumSeason{
    // 创建枚举对象
    SPRING("春", "温暖"),
    SUMMER("夏", "炎热"), // 多个 枚举对象以 英文逗号 分隔
    AUTUMN("秋", "凉爽"),
    WINTER("冬", "寒冷"); // 最后以 英文分号 结束

    private String name;
    private String desc;

    // 构造器私有化
    private EnumSeason(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "EnumSeason{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}