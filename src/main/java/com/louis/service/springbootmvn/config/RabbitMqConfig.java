package com.louis.service.springbootmvn.config;


import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


/**
 * RabbitMqConfig
 *
 * @author wangxing
 * @date 2019-5-5
 */

public class RabbitMqConfig {
    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;

    @Bean
    public Queue createQueue() {
        return new Queue(msgQueueName, true);
    }
}
