package com.zhangjian.面向对象基础.封装;


import org.jetbrains.annotations.NotNull;

public class Encapsulation {
    public static void main(String[] args) {
        // 默认构造器
        Person person = new Person();
        person.setName("Tom");
        person.setAge(130); // 年龄信息非法
        person.setSalary(5000);
        // public 属性可直接访问，private 属性只能通过公开的get方法获取
        System.out.println("name=" + person.name + " age=" + person.getAge() + " salary=" + person.getSalary("secretKey"));

        System.out.println("=================");
        System.out.println();

        // 带参构造器
        Person smith = new Person("Smith", 18, 1000);
        // 访问信息，token错误，拿不到有效薪资
        System.out.println("name=" + smith.name + " age=" + smith.getAge() + " salary=" + smith.getSalary("errorKey"));

        // 设置薪资时不合法，所以薪资是最低标准
        System.out.println("Smith 现在的工资是 " + smith.getSalary("secretKey"));
    }
}

/**
 *
 * 封装(encapsulation)就是把抽象出的数据[属性]和对数据的操作[方法]封装在一起，数据被保护在内部，
 *                   程序的其他部分只能通过被授权的操作[方法]，才能对数据进行操作。
 *
 * 封装的步骤：
 * 1、将属性进行私有化[private]，外部不能直接修改属性；
 * 2、提供一个公共的set方法，用于对属性值的校验并赋值[public]；
 * 3、提供一个公共的get方法，用于获取属性的值[public];
 *
 * idea 中可通过右键 generate 快速生成 get和set 方法
 *
 */
class Person{
    public String name;
    private int age;
    private double salary;

    // 准备两个构造器。右键-generate-选择参数生成构造器
    public Person() {
    }

    public Person(String name, int age, double salary) {
        // 传统的初始化赋值，不能使用到 set方法中的校验逻辑
        // 可以在构造器中直接使用set方法来使得构造器同样具有校验判断
//        this.name = name;
//        this.age = age;
//        this.salary = salary;

        setName(name);
        setAge(age);
        setSalary(salary);
    }

    public String getName() {
        // 公开属性获取不做限制
        return name;
    }

    public void setName(String name) {
        // 对名字长度做校验，非法则置为默认值
        if(name.length() >= 2 && name.length() <= 6) {
            this.name = name;
        }else {
            System.out.println("姓名长度非法，长度须在 2-6 范围");
            this.name = "unknownName";
        }
    }

    public int getAge() {
        // 年龄信息获取不做限制
        return age;
    }

    public void setAge(int age) {
        // 对年龄合法性做校验，不合法置为 0
        if(age >= 1 && age <= 120) {
            this.age = age;
        }else {
            System.out.println("年龄信息非法");
            this.age = 0;
        }
    }

    public double getSalary(@NotNull String token) {
        // 对于薪资敏感信息，必须使用正确的token才能获取
        if(token.equals("secretKey")) {
            return salary;
        }
        // 没有正确的返回0.0
        return 0.0;
    }

    public void setSalary(double salary) {
        // 薪资校验
        if(salary > 2148 && salary < 100000) {
            this.salary = salary;
        }else {
            System.out.println("工资不对劲，先给你搞成本地最新薪资");
            this.salary = 2148;
        }
    }
}