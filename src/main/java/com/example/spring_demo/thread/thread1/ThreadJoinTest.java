package com.example.spring_demo.thread.thread1;

public class ThreadJoinTest extends Thread {


    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println(getName() + "------------" + i);
        }
    }
}


class ThreadTest {


    public static void main(String[] args) {
        ThreadJoinTest thread = new ThreadJoinTest();
        ThreadJoinTest thread2 = new ThreadJoinTest();
        thread2.setName("thread2");
        thread.setName("thread");
        thread.start();
        try {
            thread.join(); //阻塞线程，当前线程执行完后，其他线程才能执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }

}