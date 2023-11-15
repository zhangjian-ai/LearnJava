package com.zhangjian.开箱即用.StringBuffer类;

/**
 * StringBuffer:
 *    1、java.lang.StringBuffer 代表可变的字符序列，可以对字符串内容进行增删改查
 *    2、很多方法与String相同，但 StringBuffer 是可变长度的
 *    3、StringBuffer 是一个容器
 *
 * StringBuffer 解读：
 *    1、StringBuffer 的直接父类是 AbstractStringBuilder
 *    2、父类 AbstractStringBuilder 有一个属性 byte[] value， 不是 final 属性。
 *       也就是说该 value 数组存放的字符序列，是放在堆中的(String在常量池中)
 *    3、因为 byte[] value 存放于堆中，所以是可以做修改操作的(增删改)，且不用每次都创建新的对象(当数组容量不够时，会创建更大空间的数组对象)，效率高于 String
 *    4、StringBuffer 实现了 Serializable 接口，即 StringBuffer 的对象可序列化
 *    5、StringBuffer 是一个 final 类，不能被继承
 *
 * String 和 StringBuffer 对比：
 *    1、String 保存的是字符串常量，里面的值不能更改，每次String的更新实际上是新建了字符串常量，更新变量指向了新的地址空间，效率较低
 *    2、StringBuffer 保存的是字符串变量，里面的值可以更改，通常 StringBuffer 都是直接更新 byte[] value 的内容，不用更新地址，效率较高
 *
 * StringBuffer 构造器：
 *    StringBuffer 构造器主要工作是初始化 byte[] value 属性，重载的多个构造器主要区别在于 value 数组的长度。
 *    注意：StringBuffer 构造器不能接收 null 对象，但 append 方法可以，将 null对象转为 "null"
 *       1、 public StringBuffer()  无参构造器，默认创建数组长度为 16
 *       2、 public StringBuffer(int capacity)  创建数组长度由调用方定义，长度为 capacity
 *       3、 public StringBuffer(String str)  创建数组长度为 str.length() + 16 ，然后调用 append 将 str 添加到 value数组
 *       4、 public StringBuffer(CharSequence seq) 创建数组长度为 seq.length() + 16 ，然后调用 append 将 seq 添加到 value数组
 *          4.1、CharSequence 是一个接口，AbstractStringBuilder StringBuffer 都实现了该接口
 */
public class StringBufferIntroduction {
    public static void main(String[] args) {
        // 创建 StringBuffer 对象
        createStringBuffer();

        // StringBuffer 和 String 的相互转换
        convert();

        // null 创建 StringBuffer
        demoNull();
    }

    public static void createStringBuffer(){
        // 演示使用StringBuffer不同的构造器创建对象

        StringBuffer buffer = new StringBuffer();
        System.out.println(buffer.capacity()); // 容量默认 16
        System.out.println(buffer.length()); // 0


        StringBuffer buffer1 = new StringBuffer(22);
        System.out.println(buffer1.capacity());
        System.out.println(buffer1.length());


        StringBuffer buffer2 = new StringBuffer("hello world!");
        System.out.println(buffer2.capacity()); // 长度为 下面的 length + 16 = 28
        System.out.println(buffer2.length());
        System.out.println(buffer2); // "hello world!"  StringBuffer 重写了 toString 方法，返回一个具有相同值的 String 对象


        StringBuffer buffer3 = new StringBuffer(buffer2);
        System.out.println(buffer3.capacity()); // 长度为 下面的 length + 16 = 28
        System.out.println(buffer3.length());
        System.out.println(buffer3);
    }

    public static void convert(){
        // 演示 String 和 StringBuffer 之间的转换

        // String -> StringBuffer
        String a = "hello";

        // 方式一：直接 new
        StringBuffer buffer = new StringBuffer(a);
        System.out.println(buffer);

        // 方式二：先new 再append
        StringBuffer buffer1 = new StringBuffer();
        buffer1.append(a);
        System.out.println(buffer1);


        // StringBuffer -> String
        StringBuffer world = new StringBuffer("world");

        // 方式一：直接 new
        String s = new String(world);
        System.out.println(s);

        // 方式二：使用 StringBuffer 的 toString 方法
        String s1 = world.toString();
        System.out.println(s1);
    }

    public static void demoNull(){
        // 演示用 null 创建 StringBuffer实例

        // 可以先 new 后 append
        // 原因：父类中专门实现了 appendNull 方法，用于处理此情况。朴实无华的直接转为字符串 "null"
        String a = null;
        StringBuffer buffer = new StringBuffer();
        buffer.append(a);
        System.out.println(buffer); // null

        // 直接new 会抛异常
        /*
        原因：
            public StringBuffer(String str) {
                super(str.length() + 16);
                append(str);
            }
         */
        StringBuffer buffer1 = new StringBuffer(a);
        System.out.println(buffer1);
    }
}
