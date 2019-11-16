package com.jan.alg;

public class RotatedSortedArray {
    public int getArrayIndex(int[] nums){
        if(nums[nums.length - 1] > nums[0]) return 0;
        int left = 0;
        int right = nums.length - 1;
        while(true){
            //System.out.println(left + " = " + right);
            if(left + 1 == right){
                break;
            }else{
                int center = (left + right) / 2;
                int tmp = nums[center];
                if(tmp >= nums[left]){
                    left = center;
                    continue;
                }else{
                    right = center;
                    continue;
                }
            }
        }
        return left + 1;
    }

    public int getIndex(int[] nums, int left, int right, int target){
        while(true){
            if(left < 0 || right > nums.length - 1 || right < left) return -1;
            int center = (left + right) / 2;
            if(nums[center] == target){
                return center;
            }
            System.out.println(left + " = " + right);
            if(left == right){
                return -1;
            }
            if(nums[center] > target){
                right = center - 1;
                continue;
            }
            if(nums[center] < target){
                left = center + 1;
                continue;
            }
        }
    }

    public int search(int[] nums, int target) {
        int len = nums.length;
        if(len == 0){
            return -1;
        }
        if(len == 1){
            if(target == nums[0]) return 0;
            else return -1;
        }
        int idx = getArrayIndex(nums);
        System.out.println(idx);
        int index = 0;
        if(idx == 0){
            index = getIndex(nums, 0, nums.length - 1, target);
        }else{
            if(target > nums[0]){
                index = getIndex(nums, 0, idx - 1, target);
            }else if(target == nums[0]){
                return 0;
            }else{
                index = getIndex(nums, idx, nums.length - 1, target);
            }
        }
        return index;
    }
    public static void main(String[] args) {
        int[] arr = {9, 0,2,7,8};
        RotatedSortedArray rsa = new RotatedSortedArray();
        int idx = rsa.search(arr, 3);
        System.out.println(idx);
    }
}
