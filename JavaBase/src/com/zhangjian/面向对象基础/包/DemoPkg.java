/**
 * 包的命名规则：
 *  可以包含数字、字母、下划线、点号，但不能以数字开头，且不能是java中的关键字或保留字
 *
 * 命名规范：
 *  com.公司名.项目名.模块名
 *
 * 包的本质：
 *  就是创建不同的文件夹来保存类文件，不同文件夹中可以有同名的类文件
 *
 * 包的申明：
 *  使用 package 关键字，申明当前类所在的包，包申明语句必须在java文件最上面，且一个文件中只能申明一次
 *
 * 包的使用：
 *  使用 import 关键字，导入包中的类，导入包的语句介于 package语句 和 类定义语句 之间，可以导入多个类，且导入顺序无要求。举例：
 *      导入包中的一个类 import java.utils.Scanner
 *      导入包中所有的类 import java.utils.*
 *  通常不使用*导入全部类，仅导入我们需要的类
 *
 *
 */

// 包申明语句
package com.zhangjian.面向对象基础.包;

// 导包语句
import com.zhangjian.面向对象基础.包.cat1.Cat;

public class DemoPkg {
    public static void main(String[] args) {
        Cat cat = new Cat();
        // 打印对象信息
        System.out.println(cat); // com.zhangjian.核心概念.包.cat1.Cat@35bbe5e8

        // 当不同包中有相同的类时，通过显式指定包路径来区分同名的类
        com.zhangjian.面向对象基础.包.cat2.Cat cat1 = new com.zhangjian.面向对象基础.包.cat2.Cat();
        System.out.println(cat1); // com.zhangjian.核心概念.包.cat2.Cat@5a39699c
    }
}
