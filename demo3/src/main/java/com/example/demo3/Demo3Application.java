package com.example.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
//@EnableEurekaClient
public class Demo3Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class);
        final List<String> strings = SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class, null);
        System.out.println(strings.size());
        strings.forEach(System.out::println);

    }
}
