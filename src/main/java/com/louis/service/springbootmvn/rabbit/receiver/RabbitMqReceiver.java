package com.louis.service.springbootmvn.rabbit.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * RabbitMqReceiver
 *
 * @author wangxing
 * @date 2019-5-6
 */
@Component
public class RabbitMqReceiver {

    /**
     * 监听队列
     *
     * @param message     所有信息都在里面
     * @param channel     信道
     * @param deliveryTag 投递标签
     * @throws IOException
     */
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    @RabbitHandler
    public void receiveMsg(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException, InterruptedException {
        String s = new String(message.getBody());
        System.out.printf("receiveMsg----收到消息：" + message + "\n");
        System.out.printf("receiveMsg----收到消息：" + new String(message.getBody()) + "\n");

//        Map<String, Object> header = message.getMessageProperties().getHeaders();
//        System.out.printf("receiveMsg----收到消息ID：" + header.get("spring_returned_message_correlation").toString() + "\n");


        System.out.println(s);
        channel.basicAck(deliveryTag, false);

//        if ("123".equals(msg)) {
//            System.out.printf("接受消息：" + msg + "\n");
//            channel.basicAck(deliveryTag, false);
//        } else {
//            System.out.printf("拒绝消息：" + msg + "\n");
//            channel.basicNack(deliveryTag, false, false);
//        }
//            channel.basicReject(deliveryTag, false);

//            channel.basicAck(deliveryTag, false);
//            deliveryTag:该消息的index
//            multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息。
//
//            channel.basicNack(deliveryTag, false, true);
//            deliveryTag:该消息的index
//            multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
//            requeue：被拒绝的是否重新入队列
//
//            channel.basicReject(deliveryTag:, false);
//            deliveryTag:该消息的index
//            requeue：被拒绝的是否重新入队列
    }


    /**
     * 消费正常队列
     *
     * @param message
     * @param channel
     * @param deliveryTag
     * @throws IOException
     */
//    @RabbitListener(queues = {"louis.normal.queue3"})
//    @RabbitHandler
//    public void receiveNormalMsgToDlx(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
//        System.out.printf("正常队列收到消息：" + message + "\n");
//        System.out.printf("正常队列收到消息：" + new String(message.getBody()) + "\n");
//
//        channel.basicReject(deliveryTag, true);
//
//    }


    /**
     * 消费死信队列
     *
     * @param message
     * @param channel
     * @param deliveryTag
     * @throws IOException
     */
//    @RabbitListener(queues = {"dlx.queue"})
//    @RabbitHandler
//    public void receiveDlxMsg(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
//        System.out.printf("死信队列收到消息：" + message + "\n");
//        System.out.printf("死信队列收到消息：" + new String(message.getBody()) + "\n");
//
//        channel.basicAck(deliveryTag, false);
//
//    }

}
