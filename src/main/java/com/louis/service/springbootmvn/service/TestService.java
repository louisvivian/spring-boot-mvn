package com.louis.service.springbootmvn.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * TestService
 *
 * @author wangxing
 * @date 2019-5-5
 */
@Service
public class TestService {

    @Async
    public void generateReport(){
        // 打印异步线程名称
        System.out.printf("报表线程名："+Thread.currentThread().getName());
    }
}
