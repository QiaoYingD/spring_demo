package com.example.spring_demo.zookeeper.fenbushilock;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @classDesc：功能描述：(生产订单号规则)
 * @createTime：2020/9/3
 */
public class OrderNumGenerator {

    private static int count = 0;

    //生产订单号规则方法
    public String orderNumber() {
        SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simpt.format(new Date()) + "-" + ++count;
    }

}
