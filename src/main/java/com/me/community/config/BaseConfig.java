package com.me.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author codeY
 * @version 1.0
 * @date 2021/1/10 21:43
 */
@Configuration
public class BaseConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
