package com.zhangjian.面向对象基础.多态;

/**
 * 多态的细节：
 * 1、在向上转型中已经说到，对象能访问的成员，是由 编译类型 决定的
 *    1.1、成员方法具有重写特性，对象调用方法时，如果子类自己实现了调用的方法则优先使用自己的
 *    1.2、成员属性没有重写特性，对象可以访问的属性及属性的值也都是由 编译类型 决定的，访问到的就是 编译类型 中的属性和值
 * 2、instanceof 比较操作符，用于判断对象的 运行类型 是否为 XX类型 或 XX类型的子类型
 */
public class PolymorphismAttribute {
    public static void main(String[] args) {
        // 向上转型
        // 编译类型为 Person，运行类型为 Student
        Person s1 = new Student("男", 90, "jack", 10001);

        //属性访问
        System.out.println(s1.sex); // 男
        System.out.println(s1.weight); // 100 这里访问的是编译类型 Person 中的weight

        // 运行类型的属性无法访问
        // System.out.println(s1.name);
        // System.out.println(s1.ref);

        System.out.println(s1 instanceof Person); // true
        System.out.println(s1 instanceof Student); // true

        System.out.println(">>>>>>>>>>>>");
        // 向下转型 将得到新的引用 编译类型也是Student，就可以访问 Student 里面的属性了
        Student s2 = (Student) s1;
        System.out.println(s2.name); // jack
        System.out.println(s2.ref); // 10001
        System.out.println(s2.weight); // 90 编译类型是Student，那么此时访问的则是Student中的weight
        System.out.println(s2.sex); // 男 Student 没有sex属性，将向上到其父类中查找

        System.out.println(s2 instanceof Person); // true
        System.out.println(s2 instanceof Student); // true

        System.out.println(">>>>>>>>>>>>");
        // 再次向上转型，直接将子类引用赋值给 父类引用
        Person s3 = s2;

        //属性访问
        System.out.println(s3.sex); // 男
        System.out.println(s3.weight); // 100 这里访问的是编译类型 Person 中的weight

        // 运行类型的属性无法访问
//         System.out.println(s3.name);
//         System.out.println(s3.ref);

    }
}

class Student extends Person {
    public String name;
    public int ref;
    public int weight;

    public Student(String sex, int weight, String name, int ref) {
        super(sex, weight + 10);
        this.name = name;
        this.ref = ref;
        this.weight = weight;
    }
}

class Person{
    public String sex;
    public int weight;

    public Person(String sex, int weight) {
        this.sex = sex;
        this.weight = weight;
    }
}