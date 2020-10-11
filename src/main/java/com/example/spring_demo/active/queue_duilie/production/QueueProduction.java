package com.example.spring_demo.active.queue_duilie.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @classDesc：功能描述：(队列生产者)
 * @createTime：2020/9/3
 */
@Component
public class QueueProduction {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void productMessage(String message) {
        jmsMessagingTemplate.convertAndSend(queue, "*****:" + message);
        System.out.println("生产者 send ok");
    }

    //间隔时间3秒定投
    /*@Scheduled(fixedDelay = 3000)
    public void produceMsgScheduled() {
        jmsMessagingTemplate.convertAndSend(queue, "****:" + UUID.randomUUID().toString().substring(0, 6));
        System.out.println("生产者 定时 send ok");
    }*/

}
