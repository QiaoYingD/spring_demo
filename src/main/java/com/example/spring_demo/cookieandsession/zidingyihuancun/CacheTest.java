package com.example.spring_demo.cookieandsession.zidingyihuancun;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @classDesc：功能描述：(自定义缓存类，启动线程池定期检查数据是否已过有效期)
 * @createTime：2020/9/3
 */
public class CacheTest {


    public static void main(String[] args) {
        CacheManager cacheManager = new CacheManager();
        cacheManager.put("user", "张三", (long) 5001);
        System.out.println("添加成功！！");
        //定期检查刷新数据。。。。。 开启一个线程，检查有效期。。。。。。。
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {

            @Override
            public void run() {
                cacheManager.checkValidityData();
            }
        }, 5000, TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledThreadPool.shutdown();
        CacheEntity cacheEntity = cacheManager.get("user");
        System.out.println(cacheEntity);

    }
}
