package com.example.demo.config;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Demo2Config {
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
//    @Bean
//    public DiscoveryClient discoveryClient(){
//
//        return  new DiscoveryClient();
//    }
}
