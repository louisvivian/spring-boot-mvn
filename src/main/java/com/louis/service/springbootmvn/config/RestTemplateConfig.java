package com.louis.service.springbootmvn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateConfig
 *
 * @author wangxing
 * @date 19-6-19
 */
@Configuration
public class RestTemplateConfig {
    @Bean
//    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
