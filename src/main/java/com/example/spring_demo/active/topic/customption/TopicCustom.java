package com.example.spring_demo.active.topic.customption;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class TopicCustom {


    @JmsListener(destination = "${mytopicname}", containerFactory = "topicListenerContainer")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("消费者topic收到消息：" + textMessage.getText());
    }

    @JmsListener(destination = "${mytopicname}", containerFactory = "topicListenerContainer")
    public void receive1(TextMessage textMessage) throws JMSException {
        System.out.println("消费者topic收到消息：" + textMessage.getText());
    }


}
