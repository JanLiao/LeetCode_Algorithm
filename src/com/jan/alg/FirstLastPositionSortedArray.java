package com.jan.alg;

public class FirstLastPositionSortedArray {
    public int binarySearch(int[] nums, int tartget){
        if(nums.length == 0){
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int idx = -1;
        while(true){
            if(left > right){
                break;
            }
            int center = (left + right) / 2;
            if(nums[center] == tartget) return center;
            if(nums[center] > tartget){
                right = center - 1;
                continue;
            }
            if(nums[center] < tartget){
                left = center + 1;
                continue;
            }
        }
        return idx;
    }

    public int getMinIdx(int[] nums, int left, int right, int tartget){
        int index = -1;
        if(left > right){
            return index;
        }
        int center = (left + right) / 2;
        if(nums[center] == tartget){
            index = center;
            int idx = getMinIdx(nums, left, center - 1, tartget);
            if(idx != -1){
                index = idx;
            }
        }else if(nums[center] > tartget){
            right = center - 1;
            int idx = getMinIdx(nums, left, right, tartget);
            if(idx != -1){
                index = idx;
            }
        }else{
            left = center + 1;
            int idx = getMinIdx(nums, left, right, tartget);
            if(idx != -1){
                index = idx;
            }
        }
        return index;
    }

    public int getMaxIdx(int[] nums, int left, int right, int tartget){
        int index = -1;
        if(left > right){
            return index;
        }
        int center = (left + right) / 2;
        if(nums[center] == tartget){
            index = center;
            int idx = getMaxIdx(nums, center + 1, right, tartget);
            if(idx != -1){
                index = idx;
            }
        }else if(nums[center] > tartget){
            right = center - 1;
            int idx = getMaxIdx(nums, left, right, tartget);
            if(idx != -1){
                index = idx;
            }
        }else{
            left = center + 1;
            int idx = getMaxIdx(nums, left, right, tartget);
            if(idx != -1){
                index = idx;
            }
        }
        return index;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] arr = {-1, -1};
        if(nums.length == 0) return arr;
        int idx = binarySearch(nums, target);
        int first = -1;
        int last = -1;
        if(idx == -1){
            return arr;
        }else{
            first = idx;
            last = idx;
//            System.out.println(idx);
            int left = getMinIdx(nums, 0, idx - 1, target);
            int right = getMaxIdx(nums, idx + 1, nums.length - 1, target);
            if(left != -1){
                first = left;
            }
            if(right != -1){
                last = right;
            }
            arr[0] = first;
            arr[1] = last;
            return arr;
        }
    }

    public static void main(String[] args) {
        FirstLastPositionSortedArray fa = new FirstLastPositionSortedArray();
        int[] arr = {1, 8};
        int[] a = fa.searchRange(arr, 8);
        for(int i : a){
            System.out.print(i + "\n");
        }
        System.out.println();
    }
}
