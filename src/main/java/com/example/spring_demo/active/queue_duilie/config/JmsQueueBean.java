package com.example.spring_demo.active.queue_duilie.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @classDesc：功能描述：(读取配置的队列或者topic,开启jms)
 * @createTime：2020/9/3
 */
@Component
@EnableJms
public class JmsQueueBean {
    @Value("${myqueuename}")
    private String myQueue;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(myQueue);
    }

}
