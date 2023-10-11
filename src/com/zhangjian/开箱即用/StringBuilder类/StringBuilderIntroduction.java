package com.zhangjian.开箱即用.StringBuilder类;

/**
 * StringBuilder 类：
 *    StringBuilder 和 StringBuffer 均代表可变的字符序列。
 *    他们继承了相同的父类，实现了相同的接口，存储字符序列的属性(byte[] value)也是相同的，因此它们的使用方法也基本一致
 *
 * StringBuilder 说明：
 *    1、StringBuilder 是一个可变的字符序列，兼容 StringBuffer 的API，差异在于 StringBuffer 的API是线程安全的(synchronized 修饰了API)
 *    2、StringBuilder 不是线程安全的(API 没有使用 synchronized)，它被作为 StringBuffer 的一个简易替换，当在单线程中使用时应优先使用 StringBuilder
 *    3、也是因为 StringBuffer 保证线程安全，使用线程同步锁(synchronized)，所以从效率上 StringBuffer 是稍逊于 StringBuilder 的 [获取锁、释放锁都需要开销]
 *    4、在 StringBuilder 上，主要使用其 append 和 insert 对字符序列做变更操作
 *
 * String、StringBuffer、StringBuilder 差异比较：
 *    1、StringBuilder 和 StringBuffer 非常类似，均代表可变的字符序列(字符序列存放于堆中)，切方法也一样
 *    2、String 代表不可变字符序列，字符序列放在常量池中，效率低，但复用性高(常量池中不会存在两个完全相同的序列，只可能被多个变量引用)
 *       2.1、String 对象如果存在大量的变更操作，会导致大量副本字符串存留在内存中，占用空间，影响效率
 *    3、StringBuffer 代表可变字符序列，效率较高，线程安全
 *    4、StringBuilder 代表可变字符序列，效率最高，线程不安全
 *
 * 结论：
 *    1、如果字符串存在大量修改操作，一般使用 StringBuilder 和 StringBuffer
 *    2、如果字符串存在大量修改操作，并在单线程的情况，使用 StringBuilder
 *    3、如果字符串存在大量修改操作，并在多线程的情况，使用 StringBuffer
 *    4、如果字符串很少修改，被多个对象引用，使用 String 。比如配置信息等
 *
 */
public class StringBuilderIntroduction {
    public static void main(String[] args) {
        // 在 StringBuffer 中没演示方法的使用
        // 这里通过演示 StringBuilder 的方法，一并演示，二者是相同的

        // 创建对象
        StringBuilder builder = new StringBuilder("大丈夫处世，碌碌无为");

        System.out.println(builder.capacity()); // 26  byte[] value 的容量: "大丈夫处世，碌碌无为".length() + 16
        String s = builder.toString();  // toString 返回一个 String 对象
        System.out.println(s); // 大丈夫处世，碌碌无为

        // append
        System.out.println("=====append=====");
        builder.append("，与朽木腐草何意");
        System.out.println(builder); // 大丈夫处世，碌碌无为，与朽木腐草何意

        // insert
        System.out.println("=====insert=====");
        builder.insert(6, "公瑾");
        System.out.println(builder); // 大丈夫处世，公瑾碌碌无为，与朽木腐草何意

        // delete 左开右闭
        System.out.println("=====delete=====");
        builder.delete(6,8);
        System.out.println(builder); // 大丈夫处世，碌碌无为，与朽木腐草何意

        // indexOf 获取子串在 字符序列中首次出现的位置
        System.out.println("=====indexOf=====");
        int index = builder.indexOf("朽木");
        System.out.println(index); // 12

        // charAt 获取字符串中指定所引处的字符
        System.out.println("=====charAt=====");
        char c = builder.charAt(4);
        System.out.println(c); // 世

        // substring 获取字符串的子串
        System.out.println("=====substring=====");
        String s1 = builder.substring(6); //   从起始位置开始截取，直到结束
        String s2 = builder.substring(6, 10); //   从起始位置开始截取，直到结束索引(不包含)
        System.out.println(s1); // 碌碌无为，与朽木腐草何意
        System.out.println(s2); // 碌碌无为

        // replace 替换字符串中的子串
        System.out.println("=====replace=====");
        builder.replace(6, 10, "苟且偷生");
        System.out.println(builder);


        // equals 被重写，直接判断两个对象是否是同一个
        // 这里区别于 String 的 equals 方法，在 String 中会先判断是否是同一个对象，如果不是还会检查 传入的是不是String，如果是就再判断 值是否相等
        System.out.println("=====equals=====");
        System.out.println(builder.equals("大丈夫处世，苟且偷生，与朽木腐草何意")); // false
        System.out.println(builder.toString().equals("大丈夫处世，苟且偷生，与朽木腐草何意")); // true  toString 返回新对象，但是 值 是相同的


        // setCharAt 设置指所引处的值
        System.out.println("=====setCharAt=====");
        builder.setCharAt(1, '美');
        builder.setCharAt(2, '女');
        System.out.println(builder);  // 大美女处世，苟且偷生，与朽木腐草何意
    }
}
