package com.zhangjian.集合;

import java.util.*;

public class MapIntroduction {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        HashSet list = new HashSet();

        list.add("name");
        System.out.println();
    }
}

class A {}

class Employee{
    private String name;
    private double salary;
    private MyDate birthday;

    public Employee(String name, double salary, MyDate birthday) {
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    /**
     * 仅判断 name 和 birthday
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        // 需要重写MyDate的 equals 方法
        return name.equals(employee.name) && birthday.equals(employee.birthday);
    }

    /**
     * 仅使用 name 和 birthday 生成hash值
     * @return
     */
    @Override
    public int hashCode() {
        // 内部调用每个对象自身的 hashCode
        // 那这里 就会使用到 String 和 MyDate 的 hashCode 方法
        // String 类已经重写了，只要字符序列相同hashcode就相同。因此我们还需要重写MyDate的hashCode方法
        return Objects.hash(name, birthday);
    }
}


class MyDate{
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        if (year < 1900 || year > 2015){
            throw new IllegalArgumentException("年份非法");
        }
        this.year = year;

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("月份非法");
        }
        this.month = month;

        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("日期非法");
        }
        this.day = day;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(year);
        stringBuilder.append(month);
        stringBuilder.append(day);

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDate myDate = (MyDate) o;
        return year == myDate.year && month == myDate.month && day == myDate.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }
}