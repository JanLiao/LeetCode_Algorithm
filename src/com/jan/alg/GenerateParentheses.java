package com.jan.alg;

import java.util.*;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);
    }

    public List<String> generateParenthesis2(int n) {
        List<String> list = new ArrayList<>();
        if(n == 0) return list;
        if(n == 1){
            list.add("()");
            return list;
        }
        if(n == 2){
            list.add("()()");
            list.add("(())");
            return list;
        }
        Queue<String> q1 = new LinkedList<>();
        Queue<String> q2 = new LinkedList<>();
        q1.offer("()()");
        q1.offer("(())");
        int k = 2;
        appendList(q1, q2, k, n, list);
        return list;
    }

    public void appendList(Queue<String> q1, Queue<String> q2, int k, int n, List<String> list){
        if(k == n - 1){
            while(!q1.isEmpty()){
                String s = q1.poll();
                StringBuffer sb = null;
                for (int i = 0; i < s.length(); i++) {
                    sb = new StringBuffer(s);
                    sb.insert(i, "()");
                    if(!list.contains(sb.toString())){
                        list.add(sb.toString());
                    }
                }
            }
        }else{
            while(!q1.isEmpty()){
                String s = q1.poll();
                StringBuffer sb = null;
                for (int i = 0; i < s.length(); i++) {
                    sb = new StringBuffer(s);
                    sb.insert(i, "()");
                    if(!q2.contains(sb.toString())){
                        q2.offer(sb.toString());
                    }
                }
            }
            appendList(q2, q1, ++k, n, list);
        }
    }

    public List<String> generateParenthesis1(int n) {
        List<String> combinations = new ArrayList();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
//        System.out.println(current.length);
        if (pos == current.length) {
            System.out.println(new String(current));
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }
    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        char[] c = {'c', 'd', 'f', 'g'};
        System.out.println(new String(c));
        List<String> list = gp.generateParenthesis(3);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
