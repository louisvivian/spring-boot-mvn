package com.louis.service.springbootmvn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootMvnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMvnApplication.class, args);
    }

}

