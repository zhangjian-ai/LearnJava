package com.zhangjian.反射;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        // 1 获取类的Class对象
        Class<?> cls = Class.forName("com.zhangjian.反射.Cat");

        // 2 获取 private Constructor对象
        Constructor<?> constructor = cls.getDeclaredConstructor(String.class, int.class);

        // 2.1 爆破后才能访问私有构造器
        constructor.setAccessible(true);
        Object o = constructor.newInstance("阿花", 3);
        System.out.println(o);

        // 3 获取 private Field对象
        Field age = cls.getDeclaredField("age");
        Field breed = cls.getDeclaredField("breed");

        // 3.1 爆破后才能访问
        age.setAccessible(true);
        System.out.println(age.get(o));
        age.set(o, 6);
        System.out.println(age.get(o));

        // 3.2 静态属性不依赖对象实例，是直接和类关联。因此操作数据时可以使用任意对象，包括 null
        breed.setAccessible(true);
        System.out.println(breed.get(null));
        breed.set(null, "CCat");
        System.out.println(breed.get(new Object()));

        // 4 获取 private Method 对象
        Method weight = cls.getDeclaredMethod("weight");
        Method weight2 = cls.getDeclaredMethod("weight2");

        // 4.1 通过反射机制调用方法，返回结果默认都用 Object 类型接收
        weight.setAccessible(true);
        Object invoke = weight.invoke(o);
        System.out.println(invoke);

        // 4.2 同样的，静态方法，也可以不依赖固定对象
        weight2.setAccessible(true);
        Object invoke1 = weight2.invoke(null);
        System.out.println(invoke1);
    }
}

class Cat {
    private String name;
    private int age = 8;
    private static String breed = "chinaCat";

    public Cat(){
        this.name = "白猫";
    }

    public Cat(String name) {
        this.name = name;
    }

    private Cat(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void cry(){
        System.out.println(name + " 喵喵叫~~~");
    }

    public String run(int distance){
        return name + " 跑了 " + distance  + " 米";
    }

    private double weight(){
        return 8.9;
    }

    private static double weight2(){
        return 9.8;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                '}';
    }
}