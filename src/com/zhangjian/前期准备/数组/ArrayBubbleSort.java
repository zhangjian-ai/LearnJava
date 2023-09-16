package com.zhangjian.前期准备.数组;

public class ArrayBubbleSort {
    public static void main(String[] args) {
        // 冒泡排序
        int[] nums = {13, 22, 45, 8, 11, 16, 32, 19, 21, 37, 10};

        for (int i = 0; i < nums.length - 1; i++) {
            for (int k = i + 1; k < nums.length; k++) {
                if (nums[i] > nums[k]) {
                    int temp = nums[k];
                    nums[k] = nums[i];
                    nums[i] = temp;
                }
            }
        }

        for (int num : nums) {
            System.out.println(num);
        }
    }
}
