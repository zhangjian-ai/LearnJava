package com.zhangjian.前期准备.数组;

public class ArrayYangHui {
    public static void main(String[] args) {
        /*
        打印10行杨辉三角
        1
        1 1
        1 2 1
        1 3 3 1
        1 4 6 4 1
        1 5 10 10 5 1

        规律：
        1、每一行开始和结束位置为 1；
        2、每一行的元素个数为行数；
        3、除了开始和结束位置的值，中间部分的值 a[i][j] = a[i - 1][j] + a[i - 1][j - 1]

         */

        int [][] yangHui = new int[10][];

        for (int i = 0;i < yangHui.length; i ++){
            // 每一行的长度就是当前行数
            yangHui[i] = new int[i + 1];

            // 赋值
            for (int j = 0;j < yangHui[i].length;j++){
                // 判断首位和末位的值
                if (j == 0 || j == yangHui[i].length - 1){
                    yangHui[i][j] = 1;
                    continue;
                }
                yangHui[i][j] = yangHui[i - 1][j] + yangHui[i - 1][j - 1];
            }
        }

        // 输出
        for (int[] row:yangHui){
            for(int val:row){
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}
