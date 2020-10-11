package com.example.spring_demo.thread.thread1;

import lombok.Data;

/**
 * 多线程之间通讯wait()和notify()
 * 实现偶数输出男、张三，单数输出女、小红
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        Res res = new Res();
        InpThread input = new InpThread(res);
        OutThread out = new OutThread(res);
        input.start();
        out.start();
    }
}

@Data
class Res {

    private String name;

    private String sex;

    // 写入后改为false代表未读，读取后改为true代表未写入
    private Boolean flag = false;

}

/**
 * 写入线程
 */
class InpThread extends Thread {

    public Res res;

    public InpThread(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            synchronized (res) {
                if (res.getFlag()) {
                    //当前线程等待，wait();类型与sleep 可以让当前线程从运行状态变成休眠状态，使用在多线程之间同步和synchronized一起使用
                    //wait可以释放锁，让出资源；  sleep不会释放锁，不会让出资源
                    //wait需要和notify一起使用，需要放在synchronized代码块中使用
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (count == 0) {
                    res.setName("张三、李四");
                    res.setSex("男");
                } else {
                    res.setName("小红");
                    res.setSex("女");
                }
                //实现奇数和偶数
                count = (count + 1) % 2;
                res.setFlag(true);
                //和wait一起使用，唤醒另一个线程 唤醒从阻塞状态变成运行状态
                // notifyAll 唤醒其他所有线程
                res.notify();
            }
        }
    }


}

/**
 * 读取线程
 */
class OutThread extends Thread {

    private Res res;

    public OutThread(Res res) {
        this.res = res;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (res) {
                if (!res.getFlag()) {
                    try {
                        res.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("姓名：" + res.getName() + "  性别：" + res.getSex());
                res.setFlag(false);
                //唤醒其他线程
                res.notify();
            }
        }
    }
}

