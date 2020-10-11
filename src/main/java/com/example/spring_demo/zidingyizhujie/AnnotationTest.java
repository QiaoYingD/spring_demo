package com.example.spring_demo.zidingyizhujie;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ---@Target 标识允许注解在哪里使用
 * ---@Retention标识允许方式获取信息
 *
 * @classDesc：功能描述：(自定义注解)
 * @createTime：2020/9/3
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {

    //传入的值
    String value() default "";

    String id();


}

class demo {


    private String name;

    @AnnotationTest(id = "a")
    public void add() {

    }

}
