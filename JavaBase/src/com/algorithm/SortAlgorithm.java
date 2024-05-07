package com.algorithm;

import java.util.Arrays;

public class SortAlgorithm {

    public static void main(String[] args) {
        SortAlgorithm.insertSort(new int[]{10, 6, 7, 13, 32, 17, 2, 18, 9, 33});
    }

    /**
     * 冒泡排序
     * @param nums
     * @return
     */
    public static int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
        return nums;
    }

    /**
     * 选择排序
     * @param nums
     * @return
     */
    public static int[] selectSort(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]){
                    minIndex = j;
                }
            }

            if (i != minIndex){
                int temp = nums[minIndex];
                nums[minIndex] = nums[i];
                nums[i] = temp;
            }
        }

        System.out.println(Arrays.toString(nums));
        return nums;
    }

    /**
     * 插入排序
     * @param nums
     * @return
     */
    public static int[] insertSort(int[] nums){
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 ; j--) {
                if(nums[j] >= nums[j - 1]){
                    break;
                }
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
            }
        }
        System.out.println(Arrays.toString(nums));
        return nums;
    }
}
