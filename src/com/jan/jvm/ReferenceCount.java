package com.jan.jvm;

public class ReferenceCount {
    public Object obj = null;
    private static final int num = 1024 * 1024;
    private byte[] aa = new byte[num];

    public static void main(String[] args) {
        ReferenceCount rfa = new ReferenceCount();
        ReferenceCount rfb = new ReferenceCount();
        rfa.obj = rfb;
        rfb.obj = rfa;
        rfa = null;
        rfb = null;
        System.out.println("909090");
        System.gc();
    }
}
