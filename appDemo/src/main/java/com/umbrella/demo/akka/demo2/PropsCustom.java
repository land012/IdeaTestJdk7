package com.umbrella.demo.akka.demo2;

import akka.actor.Props;

/**
 * Created by xudazhou on 2015/11/2.
 */
public class PropsCustom {
    public static <T> Props create(Class<T> tClass) {
        return Props.create(tClass);
    }
}
