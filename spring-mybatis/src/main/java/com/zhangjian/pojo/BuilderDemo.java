package com.zhangjian.pojo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * 使用构造者模式，通常将外部类构造器私有化。是用户只能使用 builder 来创建实例
 */
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BuilderDemo {
    // 属性用 final 修饰一下，不允许在初始化完成后进行修改
    private final String name;
    private final int age;
    private final double salary;

    @Override
    public String toString() {
        return "BuilderDemo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}