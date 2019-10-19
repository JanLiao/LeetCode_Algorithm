package com.jan.alg;

import java.util.*;

public class ValidParentheses {
    public boolean isValid(String s) {
        if ("".equals(s)) return true;
        Map<Character, Character> map = new HashMap<>();
        map.put('{', '}');
        map.put('(', ')');
        map.put('[', ']');
        List<Character> list = new ArrayList<>();
        char[] c = s.toCharArray();
        list.add(c[0]);
        int len = c.length;
        for (int i = 1; i < len; i++) {
            if(list.size() == 0){
                list.add(c[i]);
            }else{
                char cc = list.get(list.size() - 1);
                System.out.println(cc);
//                System.out.println(c[i]);
//                System.out.println(map.get(cc));
//                System.out.println(list.size());
                if(map.get(cc) == null){
                    list.add(c[i]);
                }else{
                    if (c[i] == map.get(cc)){
                        list.remove(list.size() - 1);
                    }else{
                        list.add(c[i]);
                    }
                }
            }
        }
        System.out.println(list.size());
        if(list.size() == 0){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        String s = "{[]}";
        ValidParentheses vp = new ValidParentheses();
        System.out.println(vp.isValid(s));

        Stack<Character> stack = new Stack<Character>();
    }
}
