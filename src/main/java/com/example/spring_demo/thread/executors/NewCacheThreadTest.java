package com.example.spring_demo.thread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 缓存线程池：线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程
 */
public class NewCacheThreadTest {


    public static void main(String[] args) {
        //创建可缓存线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        //执行了execute，表示创建了线程，类似于start
        for (int i = 0; i < 10; i++) {
            //内部类中变量必须使用final修饰
            //如果变量只赋值一次后续没有用到，则默认算是被final修饰了
            int index = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "----------" + index);
                }
            });
        }
        //关闭线程池
        executor.shutdown();
    }


}
