package com.example.demo3.controller;

//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.cloud.client.ServiceInstance;
import com.example.demo3.dto.Message;
import org.slf4j.LoggerFactory;
import  org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class NewController {
//
//    private ServiceInstance serviceInstance;
//
//    public NewController(@Qualifier("eurekaRegistration") ServiceInstance serviceInstance) {
//        this.serviceInstance = serviceInstance;
//    }

    @GetMapping("/dog")
    public  String dog(){
        return "dog dog dog";
}

    private  static final Logger LOG = (Logger) LoggerFactory.getLogger(NewController.class);

    @PostMapping("starter/")
    public  String getSignedMessage(@RequestBody Message message){
       LOG.info("Applying signature to message for userName: {}",message.getUserName());
      return message.getHeader() +"\n\n"+ message.getBody() +"\n\n" + "BR,\n" + message.getUserName();
    }



}
