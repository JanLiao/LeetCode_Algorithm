package com.jan.alg;

import java.util.*;

public class ConcatenationWords {
    public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        int len = words[0].length();
        List<List<Integer>> nums = new ArrayList<>();
        int[] idxs = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            idxs[i] = s.indexOf(words[i]);
            List<Integer> tmp = new ArrayList<>();
            String ts = s;
            while(ts.indexOf(words[i]) >= 0){
                int ix = ts.indexOf(words[i]);
                tmp.add(ix);
                ts = ts.substring(ix + 1);
            }
            nums.add(tmp);
        }
        for (int i = 0; i < idxs.length; i++) {
            System.out.println(idxs[i]);
        }
        return list;
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        int len = s.length();
        int size = words.length;
        //System.out.println(size);
        if(size == 0){
            return list;
        }
        int offset = words[0].length();
        if("".equals(words[0])){
            for (int i = 0; i < len; i++) {
                list.add(i);
            }
            return list;
        }
        if("".equals(s)){
            return list;
        }
        if(len < size * offset){
            return list;
        }
        for (int i = 0; i < len - size * offset + 1; i++) {
            String str = s.substring(i, i + size * offset);
            System.out.println(str);
            Set<Integer> set = new HashSet<>();
            for(int j = 0; j < size; j++){
                int idx = str.indexOf(words[j]);
                System.out.println(words[j] + " = " + idx);
                boolean flag = false;
                //int pre = idx;
                while(idx != -1){
                    if(set.contains(idx)){
                        idx = str.substring(idx + 1).indexOf(words[j]) + idx + 1;
                        //System.out.println("cur = " + str + " = " + idx);
                        //System.out.println("-=" + idx);
                    }else{
                        //idx = idx + pre + 1;
                        if(idx % offset == 0){
                            System.out.println(idx);
                            set.add(idx);
                        }else{
                            //flag = true;
                            break;
                        }
                    }
                }
//                if(flag){
//                    break;
//                }
            }
            if(set.size() == size){
//                List<Integer> list1 = new ArrayList<Integer>(set);
//                Collections.sort(list1);
                list.add(i);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        ConcatenationWords cw = new ConcatenationWords();
        String s = "wordgoodgoodgoodbestword";
        String[] wd = {"word","good","best","good"};
        List<Integer> list = cw.findSubstring(s, wd);
        //System.out.println(s.substring(0));
        for(int i : list){
            System.out.println(i);
        }
    }
}
