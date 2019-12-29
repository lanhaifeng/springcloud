package com.feng.springcloud.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * consumer
 * 控制器
 * 2019/12/29 22:41
 *
 * @author lanhaifeng
 * @version 1.0
 */
@RestController
public class ConsumerController {

    @RequestMapping(name = "/helloWorld", method = RequestMethod.GET)
    //为restful接口提供熔断和降级
    @HystrixCommand(fallbackMethod = "helloWorld_hystrix")
    public String helloWorld(@RequestParam(required = false) String name){
        if(StringUtils.isBlank(name)){
            throw new RuntimeException();
        }
        return "helloWorld,this is consumer!";
    }

    public String helloWorld_hystrix(String name){
        return "helloWorld,this is service can not use!";
    }
}
