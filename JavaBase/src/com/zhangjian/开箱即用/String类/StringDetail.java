package com.zhangjian.开箱即用.String类;

/**
 * 通过下面静态方法演示字符串特性，可以得到一个结论：
 *
 *   1、字符串常量在常量池中创建，一旦创建将不能被修改。引用变量重新赋值过程中，是新建了一个字符串常量，引用变量重新指向新的字符串。而不是修改原来的字符串
 *   2、除了 字符串常量 的直接运算("a" + "b" 这种，不是通过引用)得到的结果仍然是 字符串常量 外，其他场景得到的都是 字符串对象
 */
public class StringDetail {
    public static void main(String[] args) {
//        detail2();
        detail3();
//        detail4();
    }


    public static void detail1(){
        /*
        String 是一个final类，代表不可变的字符序列
        字符串是不可变的，一个字符串对象一旦被分配，其内容是不可变的
         */

        // 下面两行代码，一个创建 2个 字符串常量
        String s1 = "hello";  // 常量池找 hello 的地址空间，没有就创建。 s1 指向常量池 hello
        s1 = "haha"; // 常量池找 haha 的地址空间，没有就创建。s1 重新指向常量池 haha，之前的 hello 将留在常量池等待gc回收
    }

    public static void detail2(){
        // 下面语句创建了几个对象
        String a = "hello" + "world";

        /*
        答案：1个 字符串常量
        分析：对于 两个常量直接相加时，虚拟机并不会先到常量池分别创建两个常量，再做加法运算，因为创建出来的两个常量并不会被其他地方使用。
             因此，针对此场景 虚拟机 事先完成了字符串的拼接，然后直接到常量池创建 "helloworld"
         */
    }

    public static void detail3(){
        // 下面语句创建了几个几个对象
        String a = "hello";
        String b = "world";

        String c = a + b;

        /*
        答案：3个
        分析：
            1、String a = "hello";  在常量池创建 字符串常量，a 指向其地址
            2、String b = "world";  在常量池创建 字符串常量，b 指向其地址
            3、String c = a + b;  当两个常量池的引用做运算时，就不是直接在常量池创建新的对象了，而是新建了一个String对象，因此 c 指向的是堆中的地址，下方代码可证明。
         */

        System.out.println(c); // "helloworld"
        System.out.println(c == "helloworld"); // false  c 指向的不是堆中的地址
        System.out.println(c.intern() == "helloworld"); // true
    }

    public static void detail4(){
        // 当字符串常量 和 字符串对象做运算时，得到的结果都是 字符串对象
        String a = "hello";
        String b = new String("world");

        String c = a + b;

        System.out.println(c);
        System.out.println(c == "helloworld"); // false
        System.out.println(c.intern() == "helloworld"); // true
        System.out.println(("hello" + new String("world")) == "helloworld"); // false
        System.out.println(("hello" + new String("world")).intern() == "helloworld"); // true
    }
}
