package com.jan.alg;

public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int sum = 1;
        for (int i = 1, len = nums.length; i < len; i++) {
            if(nums[i] != nums[i - 1]){
                nums[sum++] = nums[i];
            }
        }
        return sum;
    }
    public int removeElement(int[] nums, int val) {
        int sum = 0;
        for (int i = 0, len = nums.length; i < len; i++) {
            if(nums[i] != val){
                nums[sum++] = nums[i];
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3, 4, 4, 5, 5, 6};
        RemoveDuplicates rd = new RemoveDuplicates();
        System.out.println(rd.removeDuplicates(nums));
        System.out.println(rd.removeElement(nums, 3));
    }
}
