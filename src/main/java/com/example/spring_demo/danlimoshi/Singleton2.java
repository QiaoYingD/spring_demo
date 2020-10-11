package com.example.spring_demo.danlimoshi;

/**
 * 功能描述：（单例模式：饿汉式设计模式，当class文件加载时初始化，天生线程安全）
 * 保证这个类在jvm中只有一个实例
 */
public class Singleton2 {

    private static final Singleton2 instance = new Singleton2();

    private Singleton2() {

    }

    public static Singleton2 getInstance() {
        return instance;
    }


}


class Singleton2Test {
    public static void main(String[] args) {
        Singleton2 s1 = Singleton2.getInstance();
        Singleton2 s2 = Singleton2.getInstance();
        System.out.println(s1 == s2);
    }
}