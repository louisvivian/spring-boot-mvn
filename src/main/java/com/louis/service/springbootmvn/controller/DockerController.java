package com.louis.service.springbootmvn.controller;

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

    @GetMapping("/hello")
    public String helloDocker(){
        return "docker coming";
    }

    @GetMapping("/mvn")
    public String helloMaven(){
        return "maven coming";
    }
}
