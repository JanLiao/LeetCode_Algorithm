package com.jan.alg;

public class Test {
    public static void main(String[] args) {
        StringBuffer s = new StringBuffer("asdf");
        for (int i = 0; i <= s.length(); i++) {
            StringBuffer tmp = new StringBuffer("asdf");
            System.out.println(tmp.insert(i, "12"));
        }

        String s1 = "asd";
        StringBuffer sb = new StringBuffer(s1);
        for (int i = 0; i <= s.length(); i++) {
            System.out.println(sb.insert(i, "12"));
        }
    }
}
