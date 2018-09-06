package com.wq.controller;

import com.wq.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService service;


    @RequestMapping(value = "/hi")
    public String sayHello(String name){
        return service.sayHello(name);
    }
}
