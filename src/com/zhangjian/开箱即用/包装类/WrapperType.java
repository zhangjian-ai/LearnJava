package com.zhangjian.开箱即用.包装类;

/**
 * 包装类
 *    java 针对八种基础数据类型，提供了对应的 引用类型，这些引用类型 就叫做 包装类
 *    基础类型 转成 引用类型后，就具有了 类 的特点，就可以使用类中的方法
 *
 * 基础类型 和 包装类 的关系
 *      基础类型    包装类
 *      boolean     Boolean
 *      char        Character
 *      byte        Byte
 *      short       Short
 *      int         Integer
 *      long        Long
 *      float       Float
 *      double      Double
 *
 * 六种数值类型的包装类，都继承了同一个父类 Number
 *
 * 包装类和基本数据类型的转换：
 *    1、JDK5 前需要手动转换，也就是 装箱 拆箱 的过程。 装箱： 基本类型 -> 包装类型 ，反之 为拆箱
 *    2、JDK5 及之后 java 已经默认支持自动装箱和拆箱
 *
 * String 和 包装类 的转换：
 *    编码过程中经常需要将基础数据类型转换为 String
 *
 */
public class WrapperType {
    public static void main(String[] args) {
//        demo1();
//        demo2();
        demo3();
    }

    // 本方法演示装箱和拆箱。即 基本数据类型 和 包装类 之间的转换
    public static void demo1(){
        // 手动装箱
        // 📢 注意：使用 valueOf 方法时，会使用 Integer 里面的缓存，对于 -128~127 之间的小数，Integer 会缓存创建的包装对象
        //         下次再通过 valueOf 创建相同数值的包装对象时，就不会新创建而使用缓存。
        //      !!: 然而，直接 new Integer() ，每次创建的都是新对象
        Integer integer = new Integer(15);
        Integer integer1 = Integer.valueOf(13);

        // 手动拆箱
        int i = integer.intValue();

        // 自动装箱
        Integer a = i;  // 底层仍然调用的是 Integer.valueOf

        // 自动拆箱
        int b = integer1;  // 底层仍然是调用的 intValue

        System.out.println(a);
        System.out.println(b);
    }

    // 本方法演示基本数据类型和String的转换
    public static void demo2(){
        // 补充：基础类型 与 字符串 做 加法 运算后，均自动转成 String
        System.out.println(1 + "123");
        System.out.println('a' + "123");
        System.out.println(true + "123");
        System.out.println(3.3 + "123");

        System.out.println(Integer.parseInt("321"));
        System.out.println(Long.parseLong("321"));
        System.out.println(Short.parseShort("321"));
        System.out.println(Byte.parseByte("127"));
        System.out.println(Float.parseFloat("321.1"));
        System.out.println(Double.parseDouble("321.33"));
        System.out.println(Boolean.parseBoolean("true"));
        System.out.println("321".charAt(1)); // 字符串转char，就是从字符串中拿一个字符
    }

    // 本方法演示 包装类 和 String 之间的转换
    public static void demo3(){
        // 包装类 转 字符串，三种方式
        Integer i = 10;

        System.out.println(i.toString()); // Boolean、Character、Number 都已经重写了 toString ，就是将包装类的值直接转成String
        System.out.println(String.valueOf(i)); // 这是 String 实现了很多构造器，支持各种基础类型转为 String 对象
        System.out.println(i + ""); // 这点与基础类型相同

        // 字符串转包装类
        String s = "321";

        System.out.println(new Integer(s));
        System.out.println(Integer.valueOf(s));
    }
}
