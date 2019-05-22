package com.louis.service.springbootmvn.controller;

import com.louis.service.springbootmvn.aspect.WebLog;
import com.louis.service.springbootmvn.service.RabbitMqServiceImpl;
import com.louis.service.springbootmvn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/rabbit_msg")
    public String sendMsg(String msg) {
        rabbitMqService.sendMsg(msg);
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
}
