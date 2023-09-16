package com.zhangjian.前期准备.数组;

import java.util.Arrays;

public class ArrayTwoDimensional {
    public static void main(String[] args) {
        /*
        数组是一个对象，属于引用传值。
        二维数组：即数组中的每个元素本身就是一个一维数组，内存中存放时，外层数组存放的是每个一维数组的内存地址。

        练习：创建一个二维数组并输出，每个一维数组中元素个数等于当前下标加一
        1
        2 2
        3 3 3
         */

        // 定义一个长度为3的二维数组，单内部数组长度不指定
        int[][] arr = new int[3][];

        // 赋值
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[i + 1];

            // 为数组每个下标赋相同的值
            Arrays.fill(arr[i], i + 1);
        }

        // 输出元素
        for(int[] row: arr){
            for(int val: row){
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
