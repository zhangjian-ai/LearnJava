package com.zhangjian.设计模式.构造者模式;

/**
 * 构造者模式是一种创建型设计模式，它可以用于创建复杂对象。它提供了一种更优雅和可读性更高的方式来创建对象，而不是通过多个构造函数或方法来创建对象。
 *
 * 构造者模式的核心思想是将一个复杂的对象的构建与表示分离开来，以便同样的构建过程可以创建不同的表示。
 * 这种分离可以使构建过程更加灵活，并使客户端代码更加简单，不需要关心对象的构建细节。
 *
 * Lombok库为开发者提供了 @Builder 注解，可以直接使被标记的类具有 构造者模式的特性，而不用人为编写 静态内部类 builder
 */
public class Builder {
    public static void main(String[] args) {
        // 使用无参的 builder
        Person person = new Person.builder()
                .setCity("成都")
                .setSalary(18000)
                .build();
        System.out.println(person);

        // 使用有参构造的 builder
        Person person1 = new Person.builder("校长", 53)
                .setProvince("黑龙江")
                .setSalary(7888.5)
                .build();
        System.out.println(person1);
    }
}


/**
 * 代码演示实现构造者模式的类
 */
class Person{
    private String name;
    private int age;
    private String province;
    private String city;
    private double salary;

    private Person(builder builder){
        this.name = builder.name;
        this.age = builder.age;
        this.province = builder.province;
        this.city = builder.city;
        this.salary = builder.salary;
    }

    // 静态内部类。和外部类属性保持一致
    public static class builder{
        private String name;
        private int age;
        private String province;
        private String city;
        private double salary;

        public builder(){};

        public builder(String name, int age){
            this.name = name;
            this.age = age;
        }


        public builder setProvince(String province){
            this.province = province;
            return this;
        }

        public builder setCity(String city){
            this.city = city;
            return this;
        }

        public builder setSalary(double salary){
            this.salary = salary;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", salary=" + salary +
                '}';
    }
}