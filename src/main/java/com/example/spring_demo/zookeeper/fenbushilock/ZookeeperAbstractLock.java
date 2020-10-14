package com.example.spring_demo.zookeeper.fenbushilock;

import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @classDesc：功能描述：(将重复的代码封装抽象类,具体业务逻辑有子类去实现.)
 * @createTime：2020/9/3
 */
public abstract class ZookeeperAbstractLock implements Lock {

    /**
     * load configuration file
     */
    protected static Configuration conf;

    /**
     * zookeeper集群连接地址配置
     */
    private static final String ZOOKEEPER_QUORUM = "zookeeper.quorum";

    /**
     * zookeeper超时时间配置
     */
    private static final String ZOOKEEPER_TIMEOUT = "zookeeper.timeout";

    /**
     * zookeeper节点配置
     */
    private static final String ZOOKEEPER_PATH = "zookeeper.path";

    /**
     * zookeeper连接地址
     */
    protected  String zookeeperQuorum;

    /**
     * zookeeper超时时间
     */
    protected  int zookeeperTimeout;

    /**
     * 分布式锁使用的临时检节点
     */
    protected  String zookeeperPath;

    /**
     * load zookeeper connection
     */
    protected  ZkClient zkClient;

    static {
        try {
            conf = new PropertiesConfiguration("application.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public ZookeeperAbstractLock() {
        init();
    }

    private void init() {
        zookeeperQuorum = conf.getString(ZOOKEEPER_QUORUM);
        zookeeperTimeout = conf.getInt(ZOOKEEPER_TIMEOUT);
        zookeeperPath = conf.getString(ZOOKEEPER_PATH);
        zkClient = new ZkClient(zookeeperQuorum, zookeeperTimeout);
    }

    @Override
    public void lock() {
        //tryLock() 创建zookeeper临时节点 如果创建成功返回true，否则返回false
        if (tryLock()) {
            System.out.println("获取锁到资源 get lock");
        } else {
            //等待锁的资源
            waitLock();

            // 重写获取锁的资源
            lock();
        }
    }

    protected abstract void waitLock();

    protected abstract boolean tryLock();

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        if (zkClient != null) {
            zkClient.close();
        }
    }
}
