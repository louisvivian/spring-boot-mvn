package com.louis.service.springbootmvn.service;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * RabbitMqService
 *
 * @author wangxing
 * @date 2019-5-5
 */
@Service
public class RabbitMqServiceImpl implements RabbitTemplate.ConfirmCallback {

    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMsg(String msg) {
        System.out.printf("发送消息：" + msg + "\n");
        // 设置回调
        rabbitTemplate.setConfirmCallback(this);
        if ("123".equals(msg)) {
            // 发送消息，通过msgQueueName确定队列
            rabbitTemplate.convertAndSend(msgQueueName, msg);
        } else {
            System.out.printf("没有发送\n");
        }
    }

    /**
     * 回调确认方法
     *
     * @param correlationData 唯一标识，有了这个唯一标识，我们就知道可以确认（失败）哪一条消息了
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.printf("开始确认\n");
        if (ack) {
            System.out.println("消息id为: " + correlationData + "的消息，已经被ack成功\n");
        } else {
            System.out.println("消息id为: " + correlationData + "的消息，消息nack，失败原因是：" + cause + "\n");
        }
    }
}
