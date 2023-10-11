package com.zhangjian.面向对象基础.成员方法;

public class VariableParameter {
    public static void main(String[] args) {
        /**
         * 可变参数：java允许将同一个类中 多个同名同功能 但参数个数不同的方法，封装成一个方法。
         * 基本语法：
         *     访问修饰符 返回类型 方法名(数据类型... 形参名)    注意三个点号不是省略号，是语法的一部分
         * 可变参数当作数组使用
         */

        Exercise exercise = new Exercise();
        System.out.println(exercise.showScore("狗蛋儿", 57.5, 58.0, 52.5));

        // 可变参数同样可以制动转换数据类型
        System.out.println(exercise.showScore("在深圳披巾斩棘的大水母", 90.5, 88, 79, 59.5));


    }
}


class Exercise{
    /**
     * 可变参数方法，接受 学生名字 和 任意门学科的分数，然后返回名字及总分
     */

    public String showScore(String name, double... scores){
        double totalScore = 0;
        for(double score: scores){
            totalScore += score;
        }

        return name + " " + scores.length + "门课的总成绩是: " + totalScore;
    }
}