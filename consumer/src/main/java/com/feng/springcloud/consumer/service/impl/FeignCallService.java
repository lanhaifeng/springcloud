package com.feng.springcloud.consumer.service.impl;

import com.feng.springcloud.consumer.constant.ConsumerConstant;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * consumer
 * 2019/12/27 16:06
 * feign实现服务间调用
 *
 * @author lanhaifeng
 * @since
 **/
//value填写服务名，而且FeignClient不支持服务名为下划线的，如service_name无法通过feign访问
//fallback配置熔断处理类
@FeignClient(value = ConsumerConstant.EUREKA_SERVER_SERVICE_NAME,
//        fallback = FeignCallServiceHystrix.class, 使用fallback无法捕获错误进行错误输出
        fallbackFactory = FeignCallServiceFactoryHystrix.class
)
@Component
public interface FeignCallService {

	@RequestMapping(value = "/hi", method = RequestMethod.GET)
	String helloWorld(@RequestParam("name") String name);

    @RequestMapping(value = "/notExistService", method = RequestMethod.GET)
    String notExistService();
}
