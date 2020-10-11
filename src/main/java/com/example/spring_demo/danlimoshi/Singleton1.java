package com.example.spring_demo.danlimoshi;

/**
 * 功能描述：（单例模式：懒汉式设计模式）
 * 保证这个类在jvm中只有一个实例
 */
public class Singleton1 {


    private static Singleton1 instance;

    //单例模式需要构造函数私有化，反射也不能初始化
    private Singleton1() {

    }

    /**
     * 懒汉式当你需要的时候，才会被初始化，之后都是一个实例。懒汉式线程不安全，所以需要加上同步
     */
    public static Singleton1 getInstance() {
        if (instance == null) {
            synchronized (Singleton1.class) {
                instance = new Singleton1();
            }
        }
        return instance;
    }


}

class Singleton1Test {
    public static void main(String[] args) {
        Singleton1 s1 = Singleton1.getInstance();
        Singleton1 s2 = Singleton1.getInstance();
        System.out.println(s1 == s2);
    }
}