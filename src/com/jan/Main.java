package com.jan;

import java.util.ArrayList;
import java.util.List;

public class Main {

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

    public int[] quickSort1(int[] arr, int left, int right) {
        if(left > right) {
            return arr;
        }
        int i = left;
        int j = right;
        int num = arr[i];
        while(i != j) {
            while(arr[j] >= num && i < j)
                j--;
            while(arr[i] <= num && i < j)//再找右边的
                i++;
            if(i < j){
                //交换两个数在数组中的位置
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        //最终将基准数归位
        arr[left] = arr[i];
        arr[i] = num;
        arr = quickSort1(arr, left, i-1);//继续处理左边的，这里是一个递归的过程
        arr = quickSort1(arr, i+1, right);//继续处理右边的 ，这里是一个递归的过程
        return arr;
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

    public static void main(String[] args) {
        Main m = new Main();
        int[] nums = {3, 1, 5, -1, 9, 2, 0};
        int[] arrs = {-5, 0, 0, 1, 2, 3, 4, 5};
        int[] arrs1 = {5,-7,3,-3,5,-10,4,8,-3,-8,-3,-3,-1,-8,6,4,-4,7,2,-5,-2,-7,-3,7,2,4,-6,5};
        nums = m.quicksort(nums, 0, nums.length - 1);
        for(int i = 0; i < nums.length; i++){
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        List<List<Integer>> list = m.threesum(arrs1);
        for(List<Integer> l : list){
            for(int i = 0; i < l.size(); i++){
                System.out.print(l.get(i) + " ");
            }
            System.out.println();
        }
    }
}
