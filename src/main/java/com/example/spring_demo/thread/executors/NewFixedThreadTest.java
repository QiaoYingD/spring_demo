package com.example.spring_demo.thread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 可定长度的线程池：可控制线程最大并发数，超出的线程会在队列中等待
 */
public class NewFixedThreadTest {


    public static void main(String[] args) {
        //创建一个可定长度的线程，可控制线程最大并发数，超出的线程会在队列中等待
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            //内部类中变量必须使用final修饰
            //如果变量只赋值一次后续没有用到，则默认算是被final修饰了
            int index = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "------" + index);
                }
            });
        }
        //关闭线程池
        executor.shutdown();
    }

}
