package com.zhangjian.面向对象基础.类和对象;

public class ObjectConstructor {
    public static void main(String[] args) {
        Person jack = new Person("Jack");
        System.out.println("Jack的信息：");
        System.out.println("name=" + jack.name + " age=" + jack.age);

        Person smith = new Person("Smith", 22);
        System.out.println("Smith的信息：");
        System.out.println("name=" + smith.name + " age=" + smith.age);

        // 使用默认构造器，效果同没有写构造器。此时属性都是默认值
        Person person = new Person();
        System.out.println("废人的信息：");
        System.out.println("name=" + person.name + " age=" + person.age);
    }
}

/**
 * 构造方法/构造器（constructor）：是类的一种特殊方法。
 * 特点：
 * 1、构造器的访问修饰符可以默认；
 * 2、构造器没有返回类型（void 都不能写）；
 * 3、方法名必须和类型一样；
 * 4、参数列表和成员方法规则一致；
 * 5、构造器由系统自动调用，当新的对象创建后，系统调用构造器完成对新对象的初始化；
 * 6、构造器也可以重载，用法和方法重载一样；
 * 7、当类中没有显式定义构造器时，初始化对象时使用默认构造器，形式： 类名() {};
 * 8、当类中显式定义构造器之后，那么将不能再使用默认构造器，除非再人为的定义默认构造器，但本质上就是对构造器的重载
 * 9、构造器是类中一种特殊的成员方法，不受 静态/非静态 属性、方法、代码块 的作用域影响，都可以被调用
 */
class Person{
    String name;
    int age;

    // 第1个构造器
    public Person(String pName){
        name = pName;
    }

    //第2个构造器
    public Person(String pName, int pAge){
        name = pName;
        age = pAge;
    }

    // 人为定义默认构造器
    Person(){};
}