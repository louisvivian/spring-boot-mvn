package com.louis.service.springbootmvn.controller;

import com.louis.service.springbootmvn.aspect.WebLog;
import com.louis.service.springbootmvn.entity.MerchantStoreClassItemExtendEntity;
import com.louis.service.springbootmvn.service.RabbitMqServiceImpl;
import com.louis.service.springbootmvn.service.RedisService;
import com.louis.service.springbootmvn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * DockerController
 *
 * @author wangxing
 * @date 2019-1-4
 */
@RestController
@RequestMapping("/docker")
public class DockerController {

    @Autowired
    private TestService testService;

    @Autowired
    private RabbitMqServiceImpl rabbitMqService;

    @Autowired
    private RedisService redisService;

    @Value("${MS_CONFIG_LABEL}")
    private String msConfigLabel;

    @Value("${tmall.refund.transporter.queue.requeue:true}")
    private Boolean queueRequeue;

    @GetMapping("/hello")
    @WebLog(description = "denglu")
    public String helloDocker() {
        // 包名
        String s = this.getClass().getPackage().getName();
        // 类名 全路径
        String s1 = this.getClass().getName();
        // 方法名
        String s2 = Thread.currentThread().getStackTrace()[1].getMethodName();


        return "docker coming-------" + s1 + "---" + s2;
    }

    @GetMapping("/mvn")
    public String helloMaven() {
        return "maven coming";
    }

    @GetMapping("/page")
    public String asyncPage() {
        System.out.printf("请求线程名称：" + Thread.currentThread().getName());
        testService.generateReport();
        return "async";
    }

    /**
     * RabbitMq 测试
     * http://localhost:8100/docker/rabbit_msg/louis-test-ex-direct/test1/123666
     *
     * @param exchange
     * @param routingKey
     * @param msg
     * @return
     */
    @GetMapping("/rabbit_msg/{exchange}/{routing_key}/{msg}")
    public String sendMsg(
            @PathVariable(value = "exchange", required = true) String exchange,
            @PathVariable(value = "routing_key", required = true) String routingKey,
            @PathVariable(value = "msg", required = true) String msg) {
        rabbitMqService.sendMsg(exchange, routingKey, msg);
        return "rabbit-test";
    }

    @GetMapping("/{item_id}")
    @WebLog(description = "denglu")
    public ResponseEntity<String> get(
            @PathVariable(value = "item_id", required = true) Integer itemId
    ) {
        String s1 = this.getClass().getName();
        return ResponseEntity.status(HttpStatus.OK).body(s1);
    }


    /**
     * 消息补偿 接口
     *
     * @param msg
     * @return
     */
    @GetMapping("/msg_resend/{msg}")
    public String testOthers(@PathVariable(value = "msg", required = true) String msg) {
        System.out.println("这个接口 被调用了哦哦哦哦------" + msg + "-----" + msConfigLabel + queueRequeue);
        return "rabbit-test11111";
    }


    @GetMapping("/test_http")
    public String testHttp() {
        String s = testService.testHttp();
        return s;
    }


// redis ---------------------------------------------------------------------

    /**
     * http://localhost:8100/docker/100000005/8/136/38938/5588
     *
     * @param merchantId
     * @param storeId
     * @param classId
     * @param itemId
     * @param valueId
     * @return
     */
    @GetMapping("/{merchant_id}/{store_id}/{class_id}/{item_id}/{value_id}")
    public List<MerchantStoreClassItemExtendEntity> get(
            @PathVariable(value = "merchant_id", required = true) Integer merchantId,
            @PathVariable(value = "store_id", required = true) Integer storeId,
            @PathVariable(value = "class_id", required = true) Integer classId,
            @PathVariable(value = "item_id", required = true) Integer itemId,
            @PathVariable(value = "value_id", required = true) Integer valueId
    ) {
        List<MerchantStoreClassItemExtendEntity> strRes = redisService.get(merchantId, storeId, classId, itemId, valueId);
        return strRes;
    }


    /**
     * http://localhost:8100/docker/incr
     */
    @GetMapping("/incr")
    public void getIncr() {
        redisService.getIncr();
    }

}
