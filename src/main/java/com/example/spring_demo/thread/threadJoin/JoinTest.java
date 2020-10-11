package com.example.spring_demo.thread.threadJoin;

/**
 *  join()作用是让其他线程变为等待，直到当前线程执行完毕才会释放资源，其他线程才会执行
 *  join()把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程，比如在B线程中调用了A线程的Join()方法
 *  直到线程A执行完毕后，B线程才会继续执行
 */
public class JoinTest {


    public static void main(String[] args) {
        //使用匿名内不内创建线程
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("t1,i:"+i);
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 20; i++) {
                    System.out.println("t2,i:"+i);
                }
            }
        });
        t2.start();

    }

}
