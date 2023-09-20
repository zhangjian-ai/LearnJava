package com.zhangjian.核心概念.多态;

/**
 * 多态参数：
 *   方法定义的形参类型是父类类型，实参类型允许是子类类型
 */
public class PolymorphismArguments {
    public static void main(String[] args) {
        PolymorphismArguments polymorphismArguments = new PolymorphismArguments();

        Staff hank = new Staff("Hank", 7800);
        Manager willian = new Manager("Willian", 9043.8, 15000);

        // 打印年收入
        polymorphismArguments.showAnnual(hank);
        polymorphismArguments.showAnnual(willian);

        // 干活
        polymorphismArguments.testWork(hank);
        polymorphismArguments.testWork(willian);
    }

    public void showAnnual(Employee e){
        System.out.println(e.getAnnual());
    }

    public void testWork(Employee e){
        // 向下转型
        if (e instanceof Staff){
            ((Staff) e).work();
        }

        if (e instanceof Manager){
            ((Manager) e).manage();
        }
    }

}

class Manager extends Employee{
    private double bonus; // 奖金

    public Manager(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    @Override
    public double getAnnual() {
        return super.getAnnual() + bonus;
    }

    public void manage() {
        System.out.println("Manager " + getName() + " is managing");
    }
}

class Staff extends Employee{
    public Staff(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double getAnnual() {
        return super.getAnnual();
    }

    public void work(){
        System.out.println("Staff " + getName() + " is working");
    }
}

class Employee{
    private String name;
    private double salary; // 月薪

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getAnnual(){ // 获取年薪
        return salary * 12;
    }
}