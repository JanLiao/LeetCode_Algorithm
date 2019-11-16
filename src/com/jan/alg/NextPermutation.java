package com.jan.alg;

public class NextPermutation {
    public int[] quicksort1(int[] nums, int left, int right){
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

    public int[] quicksort(int[] nums, int left, int right){
        if(left > right) return nums;
        int i = left;
        int j = right;
        int tmp = nums[i];
        while(i != j){
            while(i < j && nums[j] >= tmp) j--;
            while(i < j && nums[i] <= tmp) i++;
            if(i < j){
                int tp = nums[i];
                nums[i] = nums[j];
                nums[j] = tp;
            }
        }
        nums[left] = nums[i];
        nums[i] = tmp;
        nums = quicksort(nums, left, i - 1);
        nums = quicksort(nums, i + 1, right);
        return nums;
    }

    public int[] quicksort2(int[] nums, int left, int right){
        if(left > right) return nums;
        int i = left ;
        int j = right ;
        int tmp = nums[left];
        // 找到数组中小于tmp和大于tmp的下标索引
        while(i != j){
            // 先从右向左查找小于tmp的下标索引
            while(i < j && nums[j] >= tmp) j--;
            // 先从左向右查找大于tmp的下标索引
            while(i < j && nums[i] <= tmp) i++;
            if(i < j){
                int tp = nums[i];
                nums[i] = nums[j];
                nums[j] = tp;
            }
        }
        // 查找成功后,分治处理tmp左右两部分
        nums[left] = nums[i];
        nums[i] = tmp;
        quicksort(nums, left, i - 1);
        quicksort(nums, i + 1, right);
        return nums;
    }

    public int[] nextPermutation(int[] nums) {
        int len = nums.length;
        int p = -1;
        int q = -1;
        for(int i = len - 2; i >= 0; i--){
            if(nums[i] < nums[i + 1]){
                p = i;
                break;
            }
        }
        if(p != -1){
            for(int i = len - 1; i >= 0; i--){
                if(nums[i] > nums[p]){
                    q = i;
                    break;
                }
            }
            int tmp = nums[q];
            nums[q] = nums[p];
            nums[p] = tmp;
            quicksort(nums, p + 1, len - 1);
        }else{
            int k = len - 1;
            int center = (len - 1) / 2;
            while(k > center){
                int tt = nums[k];
                nums[k] = nums[len - 1 - k];
                nums[len - 1 - k] = tt;
                k--;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        int[] arr = {2, 3, 1, 2, 10, 5};

        int[] a = np.quicksort2(arr, 0, arr.length - 1);
        for(int i : a){
            System.out.print(i + "\t");
        }
        System.out.println();
        int[] nums = {1,6,8,5};
        nums = np.nextPermutation(nums);
        for(int i : nums){
            System.out.print(i + "\n");
        }
    }
}
