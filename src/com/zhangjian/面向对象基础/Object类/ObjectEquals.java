package com.zhangjian.面向对象基础.Object类;


/**
 * == 比较运算符：
 *   1、既可以判断基本数据类型，也可以判断引用类型；
 *   2、判断基本类型时，比较的是值是否相等；
 *   3、判断引用类型时，比较的是引用指向的是否是同一个地址；
 *
 * Object 中的 equals:
 *   1、equals方法 和 toString方法 是 Object 基类中的方法，因此java中所有对象都具有这两个方法
 *   2、equals方法只能判断引用类型，默认判断的是地址是否相等（String 字符串放在 方法区的常量池中，提供给属性的是在常量池中的地址）
 *   3、在类中往往重写该方法，用于判断内容是否相等。比如 String、Integer 等类
 *
 *
 * Object equals 源码：
 *       // 直接判断当前对象和目标对象是否是同一个
 *       public boolean equals(Object obj) {
 *         return (this == obj);
 *       }
 *
 * String equals 源码：
 *     public boolean equals(Object anObject) {
 *         if (this == anObject) { // 先判断是否为同一个对象，如果是那一定为真
 *             return true;
 *         }
 *         // 不是同一个对象，就看目标对象是不是 String
 *         if (anObject instanceof String) { // 如果目标对象是 String 类型则继续比较
 *             String aString = (String)anObject; // 形参类型是Object 实参是 String 类型，因此需要向下转型才能使用 String 的方法
 *             if (coder() == aString.coder()) {  // JDK11 比较字符集是否一致，一直就继续比较每一个值
 *                 return isLatin1() ? StringLatin1.equals(value, aString.value) // value 是一个 byte[]
 *                                   : StringUTF16.equals(value, aString.value);
 *             }
 *         }
 *         return false;
 *     }
 *
 * Integer equals 源码：
 *     public boolean equals(Object obj) {
 *         if (obj instanceof Integer) { // 判断目标对象是不是Integer，如果是，就判断其值是否相等
 *             return value == ((Integer)obj).intValue();
 *         }
 *         return false;
 *      }
 */
public class ObjectEquals {
    public static void main(String[] args) {
        // 字符串比较
        String s1 = new String("花洒打算abc");
        String s2 = new String("花洒打算abc");

        System.out.println(s1 == s2); // false 不是同一个对象
        System.out.println(s1.equals(s2)); // true

        // Integer 比较
        Integer n1 = Integer.valueOf(128);
        Integer n2 = Integer.valueOf(128);

        System.out.println(n1 == n2); // false 不是同一个对象
        System.out.println(n1.equals(n2)); // true

        Integer n3 = Integer.valueOf(127);
        Integer n4 = Integer.valueOf(127);

        System.out.println(n3 == n4); // true 是同一个对象，Integer対小数(-128~127)有做缓存，而 valueOf 就是用了缓存，对于小数不会重复创建实例
        System.out.println(n3.equals(n4)); // true

        // 直接 new Integer 就不会使用到缓存
        Integer n5 = new Integer(100);
        Integer n6 = new Integer(100);

        System.out.println(n5 == n6); // false
        System.out.println(n5.equals(n6)); // true

        // Person
        System.out.println("======Person======");
        Person_ p1 = new Person_("jelly", 28, '男');
        Person_ p2 = new Person_("jelly", 28, '男');
        Person_ p3 = new Person_("jelly", 18, '男');

        System.out.println(p1 == p2); // false
        System.out.println(p1.equals(p2)); // true
        System.out.println(p1.equals(p3)); // false

    }
}


/**
 * 实现 Person_ 类，包含 name、age、gender 三个属性，并重写 equals 方法，当这三个属性相同时，判定为相等
 */
class Person_{
    private String name;
    private int age;
    private char gender;

    public Person_(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person_)) return false;

        // 私有属性在本类中可以直接使用
        Person_ p = (Person_) o;
        return this.name == p.name && this.age == p.age && this.gender == p.gender;
    }

}

