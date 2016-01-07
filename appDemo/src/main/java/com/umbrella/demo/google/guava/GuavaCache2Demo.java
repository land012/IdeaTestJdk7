package com.umbrella.demo.google.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by xudazhou on 2016/1/7.
 */
public class GuavaCache2Demo {

    private static final Logger log = LoggerFactory.getLogger(GuavaCache2Demo.class);

//    CacheBuilder.
    private Cache<Integer, String> cache;

    @Before
    public void before() {
        cache = CacheBuilder.newBuilder().maximumSize(20).expireAfterAccess(3, TimeUnit.HOURS).build();
        cache.put(2, "v2");
    }

    @Test
    public void test1() throws Exception {
        String v = cache.get(1, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "no";
            }
        });
        log.info(v);

        if("no".equals(v)) {
            cache.put(1, "yes");
        }
        v = cache.get(1, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "no";
            }
        });
        log.info(v);
    }
}
