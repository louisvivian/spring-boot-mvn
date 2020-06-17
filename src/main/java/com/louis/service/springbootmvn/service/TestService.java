package com.louis.service.springbootmvn.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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

    /**
     * connectTimeout 设置连接超时时间
     * readTimeout    设置读取超时时间
     */
    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)
            .build();

    @Async
    public void generateReport() {
        // 打印异步线程名称
        System.out.printf("报表线程名：" + Thread.currentThread().getName());
    }

    // 下面是  定时任务 好用的哦
//    @Scheduled(cron = "0/5 * * * * ?")
//    @Async
//    public void getScheduler1() {
//        System.out.printf("线程：" + Thread.currentThread().getName() + "。任务：1。执行次数：" + iCount1 + "\n");
//        iCount1++;
//    }
//
//    @Scheduled(cron = "0/5 * * * * ?")
//    @Async
//    public void getScheduler2() {
//        System.out.printf("线程：" + Thread.currentThread().getName() + "。任务：2。执行次数：" + iCount2 + "\n");
//        iCount2++;
//    }

    public String testHttp() {
        try {
            String okUrl = "http://118.25.75.102:32673/internal/coupon/code/552767ef9a884d34b8f04a58aa23b6ea?merchant_id=100000005";

            Request request = new Request.Builder().url(okUrl).build();

            Response response = client.newCall(request).execute();
            String tok = response.body().string();
            System.out.println("okok");
            return tok;
        } catch (Exception e) {
            System.out.println("erroreeeeeeee");
            return "";
        }
    }
}
