package com.louis.service.springbootmvn.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * TestService
 *
 * @author wangxing
 * @date 2019-5-5
 */
@Service
public class TestService {

    private int iCount1 = 1;
    private int iCount2 = 1;

    @Async
    public void generateReport() {
        // 打印异步线程名称
        System.out.printf("报表线程名：" + Thread.currentThread().getName());
    }

    @Scheduled(cron = "0/5 * * * * ?")
    @Async
    public void getScheduler1() {
        System.out.printf("线程：" + Thread.currentThread().getName() + "。任务：1。执行次数：" + iCount1 + "\n");
        iCount1++;
    }

    @Scheduled(cron = "0/5 * * * * ?")
    @Async
    public void getScheduler2() {
        System.out.printf("线程：" + Thread.currentThread().getName() + "。任务：2。执行次数：" + iCount2 + "\n");
        iCount2++;
    }
}
