package com.example.spring_demo.thread.threadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal提高一个线程的局部变量，访问某个线程拥有自己的局部变量
 * ThreadLocal为每个使用改变量的线程提供独立的变量副本，所以每一个线程都可以独立的改变自己的副本，而不会影响其他线程对应的副本
 */
public class ThreadLocalTest {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    /**
     * 添加key/value键值队至threadlocal中
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    /**
     * 通过key获取threadLocal中的值
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        return map.get(key);
    }

    /**
     * 接口调用结束后，可以通过AOP的拦截器结束时给删除掉
     */
    public static void remove() {
        threadLocal.remove();
    }

    public static String username() {
        Object username = get("username");
        return returnObjectValue(username);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? null : value.toString();
    }
}
