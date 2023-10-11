package com.zhangjian.开箱即用.String类;

/**
 * String 类型：
 *    1、String 对象用于保存字符串，也就是一组 字符序列
 *    2、字符串常量是用 双引号 括起来的字符序列。例如："123" "hello" "你好"
 *    3、字符串的字符使用 Unicode 字符集编码，一个字符(不区分汉字还是字母)占两个字节
 *    4、String 类有很多构造器，能很好的支持各种 基础数据类型 转换成 String，常用的有：
 *       4.1、 public String() 创建空字符串对象
 *       4.2、 public String(String original)  用一个字符串创建一个新的字符串对象，新旧字符串对象的 字符序列 想通
 *       4.3、 public String(char value[])  用一个字符数组创建字符串
 *       4.4、 public String(char value[], int offset, int count) 用一个字符数组创建字符串，但只从 offset 位置开始取 count 个字符
 *       4.5、 public String(byte[] bytes)  用byte数组创建一个字符串
 *    5、String 类实现了 Serializable接口(可序列化成二进制，用IO传输) 和 Comparable接口(String对象可以比大小)
 *    6、String 是 final 类，不可被继承
 *    7、String 类的 字符串，本质上就是使用 byte[] 来保存的字符串的每个字符，其属性定义为 private final byte[] value;
 *       7.1、在 JDK8 及之前版本使用的是 char[] 来保存的字符
 *       7.2、改用 byte[] 的主要原因就是为了节省字符占用的内存。单纯改变数组类型还是不够的，高版本中还使用了 Latin-1 和 UTF-16 来编码英文和中文的编码问题
 *
 *
 * String 对象的创建方式：
 *    方式一：直接赋值 String s1 = "hello";
 *    方式二：调用构造器 String s2 = new String("hello");
 *
 * 创建说明：
 *    方式一：先到常量池查找是否有 "hello" 的数据空间，如果有 s1 就直接指向数据空间地址；如果没有就先创建"hello"的数据空间，然后再指向。
 *           s1 最终指向的是 常量池 中的空间地址。
 *
 *    方式二：先到堆中创建空间(这个空间就可以理解为对象)，里面维护了 value属性(private final byte[] value)。
 *           然后检查常量池中是否有 "hello" 的数据空间，没有就先创建。
 *           再然后value属性指向常量池中"hello"的地址。
 *           s2 最终则指向堆中 String对象 的地址，而 String对象中 value 属性指向 常量池 中的空间地址。
 */
public class StringIntroduction {
    public static void main(String[] args) {
        String a = "hello";
        String b = "hello";

        System.out.println(a.equals(b)); // true 判断字符串对象的值是否相等
        System.out.println(a == b); // true 判断是否指向同一地址空间


        String n = "world";  // n指向常量池的地址空间
        String m = new String("world"); // m 指向堆中的地址空间

        System.out.println(n.equals(m)); // true  值相等
        System.out.println(n == m); // false  指向不同的地址空间

        // String 的 intern 方法，返回 String 对象中，value 指向的常量池中的真实的 字符序列 地址
        System.out.println(m.intern());
        System.out.println(n.intern());

        System.out.println(n == m.intern()); // true
        System.out.println(m == m.intern()); // false  m 指向的是堆， intern 返回的是常量池的地址
    }
}
