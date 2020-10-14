package com.example.spring_demo.zookeeper.watcher;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


/**
 * 接口类watcher用于表示一个标准的事件处理器，其定义了事件通知相关的逻辑，包含KeeperState和EvenetType两个枚举类，
 * 分别代表了通知状态和时间类型，同时定义了时间的回调方法：process(WatchedEvent event)
 *
 * @classDesc：功能描述：(监听zookeeper事件)
 * @createTime：2020/9/3
 */
public class ZkClientWatcher implements Watcher {

    private static final Logger logger = LoggerFactory.getLogger(ZkClientWatcher.class);

    /**
     * load configuration file
     */
    protected static Configuration conf;

    /**
     * zookeeper集群连接地址
     */
    private static final String ZOOKEEPER_QUORUM = "zookeeper.quorum";

    /**
     * zookeeper超时时间
     */
    private static final String ZOOKEEPER_TIMEOUT = "zookeeper.timeout";

    private static ZooKeeper zooKeeper;


    static {
        try {
            conf = new PropertiesConfiguration("application.properties");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public ZkClientWatcher() {
        createConnection(conf.getString(ZOOKEEPER_QUORUM), conf.getInt(ZOOKEEPER_TIMEOUT));
    }

    /**
     * zk节点、发生变更、删除、修改 、 新增 事件通知
     *
     * @param event
     */
    @Override
    public void process(WatchedEvent event) {
        //事件状态
        Event.KeeperState keeperState = event.getState();
        //事件类型
        Event.EventType eventType = event.getType();
        //节点名称
        String path = event.getPath();
        System.out.println(
                "#####process()####调用####keeperState:" + keeperState + ",eventType:" + eventType + ",path:" + path);
        if (Event.KeeperState.SyncConnected == keeperState) {
            //连接类型
            if (Event.EventType.None == eventType) {
                // 建立zk连接
                System.out.println("建立zk连接成功!");
            }
            // 创建类型
            if (Event.EventType.NodeCreated == eventType) {
                System.out.println("####事件通知,当前创建一个新的节点####路径:" + path);
            }
            // 修改類型
            if (Event.EventType.NodeDataChanged == eventType) {
                System.out.println("####事件通知,当前创建一个修改节点####路径:" + path);
            }
            // 删除类型
            if (Event.EventType.NodeDeleted == eventType) {
                System.out.println("####事件通知,当前创建一个删除节点####路径:" + path);
            }
        }
    }


    /**
     * 创建zookeeper连接
     *
     * @param zookeeperQuorum
     * @param zookeeperTimeout
     * @throws IOException
     */
    private void createConnection(String zookeeperQuorum, int zookeeperTimeout) {
        try {
            zooKeeper = new ZooKeeper(zookeeperQuorum, zookeeperTimeout, this);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("zookeeper connection error");
        }

    }

    /**
     * 获取节点数据
     *
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String getData(String path) throws KeeperException, InterruptedException {
        byte[] btyes = zooKeeper.getData(path, this, new Stat());
        return new String(btyes);
    }

    /**
     * 创建节点
     *
     * @param path
     * @param data
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void createNode(String path, String data) throws KeeperException, InterruptedException {
        zooKeeper.exists(path, true);
        zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 修改节点
     *
     * @param path
     * @param data
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void updateNode(String path, String data) throws KeeperException, InterruptedException {
        zooKeeper.exists(path, true);
        zooKeeper.setData(path, data.getBytes(), -1);
    }

    /**
     * 删除节点
     *
     * @param path
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void deleteNode(String path) throws KeeperException, InterruptedException {
        zooKeeper.exists(path, true);
        zooKeeper.delete(path, -1);
    }

    /**
     * 关闭连接
     */
    public void close() {
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
