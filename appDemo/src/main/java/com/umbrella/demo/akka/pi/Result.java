package com.umbrella.demo.akka.pi;

/**
 * Created by xudazhou on 2015/12/29.
 */
public class Result {
    private final double value;

    Result(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
