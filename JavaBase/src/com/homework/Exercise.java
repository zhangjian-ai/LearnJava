package com.homework;


import java.util.Arrays;

class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 4, 5, 1, 2};
//        int[] nums = {3,100,200,3};
        System.out.println(solution.minNumberInRotateArray(nums));
    }

    public int minNumberInRotateArray(int[] nums) {
        int start = 0, end = nums.length - 1, mid = (start + end) / 2;

        while (start < end) {
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            }else{
                end --;
            }

            mid = (start + end) / 2;
        }

        return nums[start];
    }
}
