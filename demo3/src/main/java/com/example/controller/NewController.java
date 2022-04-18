package com.example.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewController {

    private ServiceInstance serviceInstance;

    public NewController(@Qualifier("eurekaRegistration") ServiceInstance serviceInstance) {
        this.serviceInstance = serviceInstance;
    }

  @GetMapping("/dog")
public  String dog(){
        return "dog dog dog";
}





}
