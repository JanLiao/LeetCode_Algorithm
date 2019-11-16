package com.jan.alg;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class LongValidParentheses {
    public int longestValidParentheses1(String s) {
        if("".equals(s)) return 0;
        int len = s.length();
        char[] c = s.toCharArray();
        //Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        stack.push(c[0]);
        int sum = 0;
        int max = Integer.MIN_VALUE;
        boolean flag = false;
        for(int i = 1; i < len; i++){
            if(!stack.isEmpty()){
                char t = stack.peek();
                System.out.println(" = " + t + " = " + c[i]);
                if(t == ')'){
                    stack.push(c[i]);
                    if(!flag){
                        if(sum > max){
                            max = sum;
                            sum = 0;
                        }
                    }
                    flag = false;
                    continue;
                }else{
                    if(c[i] == ')'){
                        //System.out.println("===========");
                        sum += 2;
                        stack.pop();
                        flag = true;
                        continue;
                    }else{
                        stack.push(c[i]);
                    }
                }
            }else{
                stack.push(c[i]);
            }
        }
        return sum;
    }

    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            System.out.println(left + " = " + right);
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dt = sdf.format(new Date());
        System.out.println(dt);
        LongValidParentheses lvp = new LongValidParentheses();
        String s = ")((";
        s.substring(0, 2);
        System.out.println(s.substring(0, 2));
        int num = lvp.longestValidParentheses(s);
        System.out.println(num);
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        };
    }
}
