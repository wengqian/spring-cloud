package com.wq;import com.wq.util.RedisUtil;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.test.context.junit4.SpringRunner;import java.util.concurrent.TimeUnit;/** * Created by wengqian on 2018/9/11. */@RunWith(SpringRunner.class)@SpringBootTestpublic class RedisdemoApplicationTests {    @Autowired    private RedisUtil redisUtil;    @Test    public void contextLoads() {        this.redisUtil.addKey("key3", "wengqian_java", 6000, TimeUnit.SECONDS);        String value1 = (String) this.redisUtil.getValue("key3");        System.out.println(value1);    }}