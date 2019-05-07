package com.louis.service.springbootmvn.controller;

import com.louis.service.springbootmvn.service.RabbitMqServiceImpl;
import com.louis.service.springbootmvn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/hello")
    public String helloDocker() {
        return "docker coming";
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

    @GetMapping("/rabbit_msg")
    public String sendMsg(String msg) {
        rabbitMqService.sendMsg(msg);
        return "rabbit-test";
    }
}
