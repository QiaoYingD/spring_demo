package com.example.spring_demo.zookeeper.fenbushilock;

/**
 * @classDesc：功能描述：(自定义的zookeeper分布式锁)
 * @createTime：2020/9/3
 */
public interface Lock {
    /**
     * 获取锁的资源
     */
    void lock();

    /**
     * 释放锁的资源
     */
    void unlock();
}
