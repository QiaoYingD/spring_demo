package com.example.spring_demo.zookeeper.fenbushilock;


import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 * @classDesc：功能描述：(实现锁的资源加锁和释放锁)
 * @createTime：2020/9/3
 */
public class ZookeeperDistrbuteLock extends ZookeeperAbstractLock {

    //并发包子类，CountDownLatch同步计数器，当计数器数值减为0时，所有受其影响而等待的线程将会被激活，这样保证模拟并发请求的真实性
    private CountDownLatch countDownLatch = null;


    @Override
    protected boolean tryLock() {
        try {
            this.zkClient.createEphemeral(zookeeperPath);
            return true;
        } catch (RuntimeException e) {
//            e.printStackTrace();
            return false;
        }
    }


    @Override
    protected void waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {

            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            // 节点被删除的时候，事件通知
            @Override
            public void handleDataDeleted(String s) throws Exception {
                //唤醒被等待的线程
                if (countDownLatch != null) {
                    //将数值改为0，让等待的线程激活运行
                    countDownLatch.countDown();
                    System.out.println("删除节点：" + zookeeperPath);
                }
            }
        };
        //注册到zClient中进行监听
        zkClient.subscribeDataChanges(zookeeperPath, iZkDataListener);
        if (zkClient.exists(zookeeperPath)) {
            countDownLatch = new CountDownLatch(1);
            try {
                //等待临时节点删除
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //删除监听
        zkClient.unsubscribeDataChanges(zookeeperPath, iZkDataListener);

    }


}
