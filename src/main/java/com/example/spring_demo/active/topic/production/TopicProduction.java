package com.example.spring_demo.active.topic.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

/**
 * @classDesc：功能描述：(队列生产者)
 * @createTime：2020/9/3
 */
@Component
public class TopicProduction {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    public void productMessage(String message) {
        jmsMessagingTemplate.convertAndSend(topic, "*****:" + message);
        System.out.println("生产者 send ok");
    }

}
