package com.example.spring_demo.active.topic.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
@EnableJms
public class JmsTopicBean {

    @Value("${mytopicname}")
    private String myQueue;


    @Bean
    public Topic topic() {
        return new ActiveMQTopic(myQueue);
    }

}
