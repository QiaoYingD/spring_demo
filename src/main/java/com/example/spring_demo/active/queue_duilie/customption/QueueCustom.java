package com.example.spring_demo.active.queue_duilie.customption;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @classDesc：功能描述：(队列生产者)
 * @createTime：2020/9/3
 */
@Component
public class QueueCustom {


    @JmsListener(destination = "${myqueuename}", containerFactory = "queueListenerContainer")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("消费者queue收到消息：" + textMessage.getText());
    }

    @JmsListener(destination = "${myqueuename}", containerFactory = "queueListenerContainer")
    public void receive1(TextMessage textMessage) throws JMSException {
        System.out.println("消费者queue收到消息：" + textMessage.getText());
    }
}
