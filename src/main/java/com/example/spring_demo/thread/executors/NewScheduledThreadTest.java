package com.example.spring_demo.thread.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 可定调度时间的线程：支持定时及周期性任务执行。延迟执行
 */
public class NewScheduledThreadTest {

    public static void main(String[] args) {
        //可创建固定线程池大小，
        ScheduledExecutorService scheduledExecutorPool = Executors.newScheduledThreadPool(5);
        //schedule执行定时任务
        scheduledExecutorPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("我是3秒钟之后执行");
            }
        }, 3, TimeUnit.SECONDS);

        //关闭线程池
        scheduledExecutorPool.shutdown();
    }

}
