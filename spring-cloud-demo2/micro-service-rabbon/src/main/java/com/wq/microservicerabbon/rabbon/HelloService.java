package com.wq.microservicerabbon.rabbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    private RestTemplate restTemplate;

    public String hiService(String name){
        return restTemplate.getForObject("http://MICRO-SERVICE/hello?name=" + name,String.class);
    }


    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService1(String name){
        return restTemplate.getForObject("http://MICRO-SERVICE/hello?name="+name,String.class);
    }
    private String hiError(String name){
        return "hi," + name + ", sorry, error!";
    }


}
