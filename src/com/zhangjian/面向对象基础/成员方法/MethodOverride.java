package com.zhangjian.面向对象基础.成员方法;

public class MethodOverride {
    public static void main(String[] args) {
        Person jack = new Person("Jack", 12);
        System.out.println(jack.say());

        Student smith = new Student("Smith", 14, 95271, 88.2);
        System.out.println(smith.say());
    }
}

/**
 * 方法重写
 *  就是在子类中有和父类里面相同的方法，我们就说子类重写了父类的某个方法。
 *
 * 方法重写需要满足以下条件：
 * 1、子类方法的 方法名称、形参列表 必须和父类方法的 方法名称、形参列表 完全一样；
 * 2、子类方法的 返回类型 必须和父类方法的 返回类型 一样 或者是 父类返回类型的子类；
 * 3、子类放方法不能缩小父类方法的访问权限，但是可以扩大
 *
 */

class Student extends Person{
    private int id;
    private double score;

    public Student(String name, int age, int id, double score) {
        super(name, age); // 调用父类有参构造起
        this.id = id;
        this.score = score;
    }

    // 子类重写方法时，访问权限只能大于等于父类方法的访问权限
    protected String say(){
        // 通过 super 关键字，直接调用父类的 say方法
        return super.say() + " id=" + id + " score=" + score;
    }
}


class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 默认级别
    String say(){
        return "name=" + name + " age=" + age;
    }

}
