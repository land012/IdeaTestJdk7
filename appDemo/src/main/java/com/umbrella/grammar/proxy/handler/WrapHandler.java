package com.umbrella.grammar.proxy.handler;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by 大洲 on 15-1-7.
 */
public class WrapHandler implements InvocationHandler {

    private Object target;

    public WrapHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object obj = method.invoke(this.target, args);
        System.out.println("after");
        return obj;
    }
}
