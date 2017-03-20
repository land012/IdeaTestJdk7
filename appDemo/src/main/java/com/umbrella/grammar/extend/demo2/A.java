package com.umbrella.grammar.extend.demo2;

/**
 * Created by xudazhou on 2017/3/18.
 */
public abstract class A {
    abstract void fn1();

    void fn2() {
        fn1();
    }
}
