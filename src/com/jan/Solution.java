package com.jan;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int[] quicksort(int[] nums, int left, int right){
        if(left > right) return nums;
        int i = left;
        int j = right;
        int num = nums[i];
        while(i != j){
            while(i < j && nums[j] >= num) j--;
            while(i < j && nums[i] <= num) i++;
            if(i < j){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        nums[left] = nums[i];
        nums[i] = num;
        nums = quicksort(nums, left, i - 1);
        nums = quicksort(nums, i + 1, right);
        return nums;
    }
    public List<List<Integer>> threesum(int[] nums){
        List<List<Integer>> list = new ArrayList<>();
        if(nums.length < 3){
            return list;
        }
        if(nums.length == 3){
            if((nums[0] + nums[1] + nums[2]) == 0){
                List<Integer> list1 = new ArrayList<>();
                list1.add(nums[0]);
                list1.add(nums[1]);
                list1.add(nums[2]);
                list.add(list1);
                return list;
            }else{
                return list;
            }
        }
        int len = nums.length;
        nums = quicksort(nums, 0, len - 1);
        int first = 0;
        for(int i = 0; i < len - 2; i++){
            first = nums[i];
            int left = i + 1;
            int right = len - 1;
            int offset = 0 - first;
            // 这一步能省一点时间
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            while(left < right){
                if((nums[left] + nums[right]) == offset){
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(first);
                    list1.add(nums[left]);
                    list1.add(nums[right]);
                    if(!list.contains(list1)){
                        list.add(list1);
                    }
                    left++;
                    right--;
                }else if((nums[left] + nums[right]) > offset){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return list;
    }
}
