package com.example.spring_demo.thread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单例线程池：它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
 * 不常用，基本上都不用
 */
public class NewSingleThreadTest {


    public static void main(String[] args) {
        //单例线程池,它只会用唯一的线程执行任务
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //执行了execute，表示创建了线程，类似于start
        for (int i = 0; i < 10; i++) {
            //内部类中变量必须使用final修饰
            //如果变量只赋值一次后续没有用到，则默认算是被final修饰了
            int index = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "---------" + index);
                }
            });
        }
        //关闭线程池
        executor.shutdown();
    }


}
