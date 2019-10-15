package com.jan.alg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LCPN {
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        Map<Integer, String> map = new HashMap<>();
        if("".equals(digits)) return list;
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        char[] c = digits.toCharArray();
        for(char cc : c){
            if((cc - '0') < 2 || (cc - '0') > 9){
                return list;
            }
        }
        list.add("");
        for(char cc : c){
            int offset = cc - '0';
            list = append(list, map.get(offset));
        }
        return list;
    }

    public List<String> append(List<String> l, String s){
        List<String> list = new ArrayList<>();
        char[] c = s.toCharArray();
        for(String ss : l){
            for(char cc : c){
                list.add(ss + cc);
            }
        }
        return list;
    }

    public List<String> letterCombinations1(String digits){
        List<String> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
        char[] c = digits.toCharArray();
        List<String> cl = new ArrayList<>();
        List<Integer> numl = new ArrayList<>();
        int total = 0;
        for(int i = 0; i < c.length; i++){
            if((c[i] - '0') < 2 && (c[i] - '0') > 9){
                return list;
            }else{
                String s = map.get("" + c[i]);
                total += s.length();
                cl.add(s);
                numl.add(0);
            }
        }
        int len = cl.size();
        int num = 0;
        // 第几个
        int numidx = 0;
        // 第几个下索引
        int idx = 0;
        while(num < total){
            System.out.println();
            String s = "";
            for(int i = 0; i < len; i++){
                s += cl.get(i).substring(numl.get(i), numl.get(i) + 1);
            }
            list.add(s);
            idx++;
            if(idx >= numl.get(numidx)){
                idx = 0;
                numidx++;

            }
            num++;
        }
        return list;
    }

    public static void main(String[] args){
        String s = "123";
        LCPN lcpn = new LCPN();
        List<String> list = lcpn.letterCombinations(s);
        for(String ss : list){
            System.out.print(ss + "\t");
        }
        System.out.println();
    }
}
