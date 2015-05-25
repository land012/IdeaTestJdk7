package com.umbrella.demo.google.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.umbrella.vo.User;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by 大洲 on 15-1-7.
 */
public class CacheDemo {

    private static final Logger log = Logger.getLogger(CacheDemo.class);

    private LoadingCache<Long, User> userCache = CacheBuilder.newBuilder()
            .maximumSize(2000)
            .expireAfterAccess(1, TimeUnit.MINUTES)
            .build(new CacheLoader<Long, User>() {
                @Override
                public User load(Long aLong) {
                    return usersMap.get(aLong);
                }
//                @Override
//                public User load(Long aLong) throws Exception {
//                    return usersMap.get(aLong);
//                }
            });

    private static Map<Long, User> usersMap; // 数据源

    @BeforeClass
    public static void beforeClass() {
        usersMap = new ConcurrentHashMap<Long, User>();
        User u1 = new User();
        u1.setId(1);
        u1.setUserName("Leah Dizon");
        User u2 = new User();
        u2.setId(2);
        u2.setUserName("Alphonse Elric");
        User u3 = new User();
        u3.setId(3);
        u3.setUserName("Mikasa");
        User u4 = new User();
        u4.setId(4);
        u4.setUserName("Oda Nobunaga");
        User u5 = new User();
        u5.setId(5);
        u5.setUserName("Orochimaru");
        usersMap.put(1L, u1);
        usersMap.put(2L, u2);
        usersMap.put(3L, u3);
        usersMap.put(4L, u4);
        usersMap.put(5L, u5);
    }

    /**
     * get(K, Callable<V>)
     */
    @Test
    public void test1() {
        User u1 = getUser(5L);
        System.out.println(u1);

        // 记录不存在
//        User u2 = getUser(7L);
//        System.out.println(u2);

//        User u3 = getUser(3L);
//        System.out.println(u3);
    }

    /**
     * 使用默认加载方法
     * @throws ExecutionException
     */
    @Test
    public void  test2() throws ExecutionException {
        User u1 = userCache.get(3L);
        System.out.println(u1);
    }

    /**
     * 查找不存在的记录，异常：
     * com.google.common.cache.CacheLoader$InvalidCacheLoadException: CacheLoader returned null for key 9.
     * @throws ExecutionException
     */
    @Test
    public void test3() {
        try {
            User u1 = userCache.get(9L);
            System.out.println(u1);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取不存在的记录，抛异常：
     * com.google.common.cache.CacheLoader$InvalidCacheLoadException: CacheLoader returned null for key 9.
     */
    @Test
    public void test4() {
        // 方法 getUnchecked 本身没有声明异常抛出
        User u1 = userCache.getUnchecked(9L);
        System.out.println(u1);
    }

    /**
     * ========================================= 无聊的分割线 =============================================
     */

    /**
     * 封装一个 get 方法
     * @param id
     * @return
     */
    private User getUser(final Long id) {
        User u = null;
        try {
            /*
             * 返回 null 时的异常
             * com.google.common.cache.CacheLoader$InvalidCacheLoadException: CacheLoader returned null for key 7.
             */
            u = userCache.get(id, new Callable<User>() {
                @Override
                public User call() throws Exception {
                    /*
                     * 如果这里返回null，会抛运行时异常
                     * com.google.common.cache.CacheLoader$InvalidCacheLoadException
                     */
                    if(true) throw new RuntimeException("异常");
                    return usersMap.get(id);
                }
            });
        } catch (Exception e) {
            log.info("can not find,id=" + id, e);
//            e.printStackTrace();
        }
        return u;
    }
}
