package com.example.spring_demo.zookeeper.fenbushilock;

/**
 * @classDesc：功能描述：(订单服务,创建订单)
 * @createTime：2020/9/3
 */
public class OrderService implements Runnable {

    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
//    private Object object = new Object();
    private Lock lock = new ZookeeperDistrbuteLock();

    @Override
    public void run() {
        getOrderNumber();
    }


    public void getOrderNumber() {
        lock.lock();
        String number = orderNumGenerator.orderNumber();
        System.out.println(Thread.currentThread().getName() + ",生成订单号：" + number);
        lock.unlock();
    }

}
