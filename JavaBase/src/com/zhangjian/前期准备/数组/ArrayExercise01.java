package com.zhangjian.前期准备.数组;

public class ArrayExercise01 {
    public static void main(String[] args) {
        /*
        使用数组打印 A-Z 字符
         */

        char[] chars = new char[26]; // 定义char类型数组，数组长度26
        // 动态赋值
        for(int i = 0; i < chars.length; i++){
            chars[i] = (char)('A' + i); // char 和 int 运算后，结果为 int，使用强转将 int 转为对应的 ascii 码
        }
        // 输出
        System.out.println("===数组chars的值如下");
        for (char aChar : chars) {  // for循环遍历数组的另一种写法，更方便
            System.out.print(aChar + " ");
        }

        System.out.println("\n\n++++ 三八线 ++++\n");

        /*
        找出数组 {110, 23.7, 121.3, 99, 68.44, 55} 中的最大值
         */
        // 静态赋值
        double[] nums = {110, 23.7, 121.3, 99, 68.44, 55};

        int idx = 0;

        for(int i = 0; i < nums.length; i++){
            if (nums[idx] < nums[i]){
                idx = i;
            }
        }

        System.out.printf(">>>数组nums中的最大值为: %f , 对应下标为: %d%n", nums[idx], idx);
    }
}
