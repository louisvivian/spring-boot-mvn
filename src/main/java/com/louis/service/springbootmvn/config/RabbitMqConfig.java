package com.louis.service.springbootmvn.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * RabbitMqConfig
 *
 * @author wangxing
 * @date 2019-5-5
 */
@Configuration
public class RabbitMqConfig {
//    @Value("${rabbitmq.queue.msg}")
//    private String msgQueueName;
//
//    @Bean
//    public Queue createQueue() {
//        return new Queue(msgQueueName, true);
//    }


    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public RabbitAdmin rabbitAdmin() {
        String strExchangeName = "normal.exchange.fanout";
        String strQueueName = "louis.normal.queue3";
        String strQueueName2 = "louis.normal.queue2";

        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);


        // 声明交换机
        Exchange exchange = ExchangeBuilder.fanoutExchange(strExchangeName).durable(true).build();
        rabbitAdmin.declareExchange(exchange);

        // 声明队列
        Map<String, Object> arguments = new HashMap<>(2);
        arguments.put("x-dead-letter-exchange", "dlx.exchange.fanout");
//        arguments.put("x-message-ttl", 30000);
        Queue itemQueue = QueueBuilder.durable(strQueueName).withArguments(arguments).build();
        rabbitAdmin.declareQueue(itemQueue);
        Queue itemQueue2 = QueueBuilder.durable(strQueueName2).withArguments(arguments).build();
        rabbitAdmin.declareQueue(itemQueue2);


        // 将队列和交换机绑定
        rabbitAdmin.declareBinding(BindingBuilder.bind(itemQueue).to(exchange).with("").noargs());
        rabbitAdmin.declareBinding(BindingBuilder.bind(itemQueue2).to(exchange).with("").noargs());



        return rabbitAdmin;
    }

}
