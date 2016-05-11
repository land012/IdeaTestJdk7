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

    /**
     * 这种写法会抛异常
     * java.lang.NullPointerException
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        String str1 = cache.get(2, null);
        log.info(str1);
    }

    /**
     * 不会抛异常
     * @throws Exception
     */
    @Test
    public void test3_1() throws Exception {
        String str2 = cache.get(2, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
        log.info(str2); // v2
    }

    /**
     * com.google.common.cache.CacheLoader$InvalidCacheLoadException: CacheLoader returned null for key 3.
     * @throws Exception
     */
    @Test
    public void test3_2() throws Exception {
        String str2 = cache.get(3, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });
        log.info(str2);
    }

    /**
     * 不会抛异常，返回 空串
     * @throws Exception
     */
    @Test
    public void test3_3() throws Exception {
        String str2 = cache.get(3, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        });
        log.info(str2);
    }

    /**
     * cleanUp
     * 清除已经过期，但还没有被清理的 key
     */
    @Test
    public void test4() {
        log.info(cache.size() + ""); // 1
        cache.cleanUp();
        log.info(cache.size() + ""); // 1
    }

    /**
     * 清除缓存
     */
    @Test
    public void test5() {
        log.info(cache.size() + ""); // 1
        cache.invalidateAll();
        log.info(cache.size() + ""); // 0
        cache.put(3, "v3");
        cache.put(4, "v4");
        log.info(cache.size() + ""); // 2
    }
}
