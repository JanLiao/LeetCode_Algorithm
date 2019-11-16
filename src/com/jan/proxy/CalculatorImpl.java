package com.jan.proxy;

public class CalculatorImpl implements Calculator {
    @Override
    public int add(int x, int y) {
        return (x + y);
    }

    @Override
    public int subtract(int x, int y) {
        return (x - y);
    }
}
