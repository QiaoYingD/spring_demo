package com.example.spring_demo.active;

import com.example.spring_demo.active.queue_duilie.production.QueueProduction;
import com.example.spring_demo.active.topic.production.TopicProduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActiveTest {


    @Autowired
    private QueueProduction queueProduction;

    @Autowired
    private TopicProduction topicProduction;

    /**
     * queue  点对点收发消息
     *
     * @param message
     */
    @GetMapping("/sendQueueMessage")
    public void sendQueueMessage(String message) {
        queueProduction.productMessage(message);
    }

    /**
     * topic  一对多收发消息
     *
     * @param message
     */
    @GetMapping("/sendTopicMessage")
    public void sendTopicMessage(String message) {
        topicProduction.productMessage(message);
    }


}
