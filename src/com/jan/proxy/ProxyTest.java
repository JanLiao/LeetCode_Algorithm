package com.jan.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) throws Throwable {
        Class calculatorCalzz = Proxy.getProxyClass(Calculator.class.getClassLoader(), Calculator.class);
        Constructor constructor = calculatorCalzz.getConstructor(InvocationHandler.class);
        Calculator calculatorImpl = (Calculator) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                CalculatorImpl impl = new CalculatorImpl();
                Object obj = method.invoke(impl, args);
                return obj;
            }
        });
        int add = calculatorImpl.add(5, 9);
        int sub = calculatorImpl.subtract(9, 5);
        System.out.println(add);
        System.out.println(sub);
    }
}
