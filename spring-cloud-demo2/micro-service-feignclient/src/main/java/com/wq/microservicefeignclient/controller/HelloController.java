package com.wq.microservicefeignclient.controller;import com.wq.microservicefeignclient.entity.User;import com.wq.microservicefeignclient.feign.FeignClient;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.RestController;/** * Created by wengqian on 2018/6/15. */@RestControllerpublic class HelloController {    @Autowired    FeignClient feignClient;    @RequestMapping(value = "/hello0")    public String hello0( @RequestParam String name) {        return feignClient.hello(name);    }    @RequestMapping(value = "/hello")    public String hello( @RequestParam String name) {//        try{//            Thread.sleep(5000);//        }catch (Exception e){////        }        return feignClient.hello(name);    }    @RequestMapping(value = "/hello1")    public String hello1( @RequestParam String name) {        User user = new User();        user.setName(name);        return feignClient.hello(user);    }    @RequestMapping(value = "/hello2")    public String hello( @RequestBody User user) {        return feignClient.hello(user);    }}