package com.example.spring_demo.active.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * 配置 topic、queue模式，支持客户端可以同时获取点对点和一对多（发布订阅）模式
 */
@Configuration
public class ListenerContainerConfig {


    /**
     * Topic模式
     *
     * @param connectionFactory
     * @return
     */
    @Bean("topicListenerContainer")
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    /**
     * Queue模式
     *
     * @param connectionFactory
     * @return
     */
    @Bean("queueListenerContainer")
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

}
