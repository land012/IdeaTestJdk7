package com.umbrella.demo.akka.pi;

import scala.concurrent.duration.Duration;

/**
 * Created by xudazhou on 2015/12/29.
 */
public class PiApproximation {
    private final double pi;
    private final Duration duration;

    PiApproximation(double pi, Duration duration) {
        this.pi = pi;
        this.duration = duration;
    }

    public double getPi() {
        return pi;
    }

    public Duration getDuration() {
        return duration;
    }
}
