package com.jan.alg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
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

        System.out.println(new Test().isNumeric("1pic"));
    }
}
