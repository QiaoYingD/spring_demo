package com.example.spring_demo.zidingyizhujie;

import java.util.ArrayList;

/**
 * 注解分类：内置注解（也称为元注解）、自定义注解（Spring框架）
 *
 * @classDesc：功能描述：(元注解)
 * @createTime：2020/9/3
 */
public class User extends Object {

    /**
     * object有哪些注解
     *
     * @Override 标识子类重写父类方法
     * @Deprecated 标识过时的意思
     * @SuppressWarnings 去除警告
     */
    @Override
    public String toString() {
        return super.toString();
    }

    @Deprecated
    static public void add() {
    }

    @SuppressWarnings("all")
    public static void main(String[] args) {
        ArrayList arr1 = new ArrayList();
    }
}
