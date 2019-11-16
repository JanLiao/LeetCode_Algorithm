package com.jan.alg;

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int idx = -1;
        if(nums.length == 0){
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while(true){
            if(left > right){
                break;
            }
            int center = (left + right) / 2;
            idx = center;
            if(nums[center] == target){
                break;
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
        if(nums[idx] == target){
            return idx;
        }else if(nums[idx] > target){
            return idx;
        }else{
            return idx + 1;
        }
    }

    public static void main(String[] args) {
        SearchInsertPosition sip = new SearchInsertPosition();
        int[] arr = {1,3};
        int idx = sip.searchInsert(arr, 2);
        System.out.println(idx);
    }
}
