package com.zhangjian.面向对象高级.枚举类型;

/**
 * 使用 enum 关键字 创建的枚举类，继承了 Enum类 的同时也内置了一些其他方法，下面是是一些从父类继承或植入的常用方法：
 *    1、toString  Enum类已经重写了，返回的是 当前枚举对象的名字(就是接收 枚举对象 的那个变量的名字)
 *    2、name  Enum实现的，返回内容与 toString 一样，但此方法是 final 方法，不能重写
 *    3、ordinal  Enum实现的，返回当前枚举对象的序号(就是在定义时的顺序)，默认从0开始
 *    4、values  定义时植入的，返回当前枚举类型的数组，其中是枚举类中定义的枚举对象
 *    5、valueOf  Enum类实现的，查找枚举类中是否有这个 名字 的枚举对象，没有就报错
 *    6、compareTo  Enum类实现的，比较两个 枚举对象 的 ordinal，返回的是 当前的对象序号与目标对象序号的差值
 */
public class UseEnumKeywordMethod {
    public static void main(String[] args) {
        // toString
        System.out.println(SeasonMethod.SPRING);
        System.out.println(Gender.MAN);

        // name
        System.out.println(SeasonMethod.AUTUMN.name());

        // ordinal
        System.out.println(SeasonMethod.SPRING.ordinal());
        System.out.println(SeasonMethod.SUMMER.ordinal());

        // values
        for (SeasonMethod season:
             SeasonMethod.values()) {
            System.out.print(season);
            System.out.print(" ");
        }
        System.out.println();
        for (Gender g: Gender.values()
             ) {
            System.out.print(g);
            System.out.print(" ");
        }
        System.out.println();

        // valuesOf
        System.out.println(SeasonMethod.valueOf("SPRING"));
        System.out.println(Gender.valueOf("MAN"));

        // compareTo
        System.out.println(SeasonMethod.SPRING.compareTo(SeasonMethod.AUTUMN)); // -2
        System.out.println(SeasonMethod.WINTER.compareTo(SeasonMethod.SPRING)); // 3

    }
}


enum SeasonMethod{
    // 创建枚举对象
    SPRING("春", "温暖"),
    SUMMER("夏", "炎热"), // 多个 枚举对象以 英文逗号 分隔
    AUTUMN("秋", "凉爽"),
    WINTER("冬", "寒冷"); // 最后以 英文分号 结束

    private String name;
    private String desc;

    // 构造器私有化
    private SeasonMethod(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}

enum Gender{
    // 使用默认构造器时，可以直接省略 ()
    MAN,
    WOMAN;
}
