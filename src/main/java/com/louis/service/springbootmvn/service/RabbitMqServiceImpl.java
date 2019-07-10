package com.louis.service.springbootmvn.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;


/**
 * RabbitMqService
 *
 * @author wangxing
 * @date 2019-5-5
 */
@Service
public class RabbitMqServiceImpl implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    /*
        如果消息没有到exchange,则confirm回调,ack=false
        如果消息到达exchange,则confirm回调,ack=true
        exchange到queue成功,则不回调return
        exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
    */


    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }


    public void sendMsg(String exchange, String routingKey, String msg) {
        System.out.printf("sendMsg----发送消息：" + msg + "\n");
//        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(exchange, routingKey, msg, correlationId);
        System.out.printf("sendMsg----消息发送完毕\n");
    }

    /**
     * 回调确认方法 消息是否发送到交换机的回调确认
     *
     * @param correlationData 唯一标识，有了这个唯一标识，我们就知道可以确认（失败）哪一条消息了
     * @param ack             true:成功，false:失败
     * @param cause           失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.printf("confirm----开始确认\n");
        if (ack) {
            System.out.println("confirm----消息id为: " + correlationData + "的消息，已经到达交换机\n");
        } else {
            System.out.println("confirm----消息id为: " + correlationData + "的消息，没有到达交换机，失败原因是：" + cause + "\n");
        }
    }

    /**
     * 交换机路由消息到队列异常方法
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {


        System.out.println("return----消息：" + message + " 发送失败");
        System.out.println("return----消息：" + new String(message.getBody()) + ",replyCode:" + replyCode + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);


    }
}
