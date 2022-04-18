package com.example.demo.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class EurekaController {
    private  final RestTemplate restTemplate;
    private  final EurekaClient client;

    public EurekaController(RestTemplate restTemplate, EurekaClient client) {
        this.restTemplate = restTemplate;
        this.client = client;
    }








    @GetMapping("demo2/dogdog")
    public String dog() {


        Applications applications = client.getApplications();
applications.getRegisteredApplications("demo3").getName();
        String homeUrl = applications.getRegisteredApplications("demo3").getInstances().get(0).getHomePageUrl();
        System.out.println( applications.getRegisteredApplications("demo3").getInstances().get(0).getHomePageUrl());
        String forObject = restTemplate.getForObject(homeUrl + "demodemo/dog", String.class);
        return forObject;

        // return restTemplate.getForObject("http://localhost:8004/demodemo/dog",String.class);

    }
}
