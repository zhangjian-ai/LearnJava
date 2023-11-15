package com.zhangjian.面向对象基础.多态;

/**
 * 多态数组：
 * 数组的定义类型是父类类型，里面保存的元素类型是子类类型
 */
public class PolymorphismArray {
    public static void main(String[] args) {
        // 多态数组，本质上也是向上转型
        // 一个人类，两个学生，两个老师。分别调用say和自己特有的方法
        Human[] persons = new Human[5];

        persons[0] = new Human("亚当", 103268);
        persons[1] = new Sdt("杰克", 30, 78);
        persons[2] = new Sdt("rouse", 22, 99);
        persons[3] = new Tcr("Max", 54, 45666);
        persons[4] = new Tcr("Seeker", 23, 1950);

        for (Human person : persons) {
            // 调用say，父类和子类都有实现，优先使用子类自己的
            System.out.println(person.say());

            // 调用子类特有方法
            if (person instanceof Sdt) {
                // 访问子类特有方法，必须向下转型
                ((Sdt) person).study();
            }

            if (person instanceof Tcr) {
                ((Tcr) person).teach();
            }
        }


    }
}

class Tcr extends Human {
    private double salary;

    public Tcr(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    @Override
    public String say() {
        return super.say() + " " + salary;
    }

    public void teach() {
        System.out.println("老师 " + getName() + " 正在教学java...");
    }
}

class Sdt extends Human {
    private int score;

    public Sdt(String name, int age, int score) {
        super(name, age);
        this.score = score;
    }

    @Override
    public String say() {
        return super.say() + " " + score;
    }

    public void study() {
        System.out.println("学生 " + getName() + " 正在学java...");
    }
}

class Human {
    private String name;
    private int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
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

    public String say() {
        return name + " " + age;
    }
}