package com.jan.alg;

public class ImplementstrStr {
    public int strStr(String haystack, String needle) {
        if(needle.equals("")){
            return 0;
        }

        if(haystack.indexOf(needle) == -1){
            return -1;
        }

        return haystack.indexOf(needle);
    }
    public int strStr1(String haystack, String needle) {
        if("".equals(needle) && "".equals(haystack)){
            return 0;
        }
        if("".equals(needle)){
            return -1;
        }
        char[] a = haystack.toCharArray();
        char[] b = needle.toCharArray();
        int len = b.length;
        int offset = 0;
        int sum = 0;
        int idx = -1;
        int le = a.length;
        int i = 0;
        while(i < le){
            if(a[i] == b[offset]){
                offset++;
                sum++;
                if(sum == len){
                    idx = i - len + 1;
                    break;
                }
                i++;
            }else{
//                System.out.println(i + " = " + sum);
                if(sum == 0){
                    i++;
                }else{
                    i = i - sum + 1;
                    sum = 0;
                    offset = 0;
                }
            }
        }
        return idx;
    }
    public static void main(String[] args) {
        String s = "mississippi";
        String t = "issip";
        ImplementstrStr is = new ImplementstrStr();
        System.out.println(is.strStr(s, t));
    }
}
