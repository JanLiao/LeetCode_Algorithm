package com.jan.alg;

public class Divide {
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE;
        }
        boolean minus = (dividend < 0) ^ (divisor < 0);
        int a =  Math.abs(dividend);
        int b =  Math.abs(divisor);
        // System.out.println(a);
        System.out.println(a - b);
        int result = 0;
        while(a - b >= 0){
            int sum = b;
            int r = 1;
            while(a - sum - sum >= 0){
                sum += sum;
                r += r;
            }
            result += r;
            a -= sum;
        }
        System.out.println(result);
        return (!minus) ? result:(-result);
    }
    public int divide1(int dividend, int divisor){
        long dd = dividend;
        long ds = divisor;
        boolean flag = true;
        if(dividend == -2147483648 && divisor == 1){
            return -2147483648;
        }
        if(dividend == -2147483648 && divisor == -1){
            return 2147483647;
        }
        if(dividend < 0){
            dd = -dd;
            flag = isCheck(flag);
        }
        if(divisor < 0){
            ds = -ds;
            flag = isCheck(flag);
        }
        int sum = 0;
        while(dd >= ds){
            dd = dd - ds;
            sum++;
        }

        if(flag){
            return sum;
        }else{
            return -sum;
        }
    }

    public boolean isCheck(boolean flag){
        if(flag){
            return false;
        }else{
            return true;
        }
    }

    public static void main(String[] args) {
        int a = -2147483648;
        //System.out.println(-a);
        int b= 2;
        Divide d = new Divide();
        System.out.println(d.divide(a, b));

        System.out.println(0 ^ 1);
        String s = "";
    }
}
