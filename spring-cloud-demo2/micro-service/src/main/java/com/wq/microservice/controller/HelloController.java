package com.wq.microservice.controller;import com.wq.microservice.entity.User;import org.springframework.web.bind.annotation.*;/** * Created by wengqian on 2018/6/15. */@RestControllerpublic class HelloController {    @RequestMapping(value = "/hello")    public String hello( @RequestParam String name) {        try {            Thread.sleep(30000);        }catch (Exception e){        }        return "hello "+name+"，this is first messge(micro-service)";    }    @RequestMapping(value = "/hello1")    public String hello( @RequestBody User user) {        return "hello "+user.getName()+"，this is first messge(micro-service)";    }}