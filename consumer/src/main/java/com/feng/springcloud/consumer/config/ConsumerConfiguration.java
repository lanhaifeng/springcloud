package com.feng.springcloud.consumer.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * consumer
 * 2019/12/27 15:31
 * 配置类
 *
 * @author lanhaifeng
 * @since
 **/
@Configuration
//将服务注册到注册中心
@EnableDiscoveryClient
//启用feiginClient功能
@EnableFeignClients(basePackages = "com.feng.springcloud.consumer")
@ComponentScan("com.feng.springcloud.consumer")
public class ConsumerConfiguration {

	@Bean("ribbonRestTemplate")
	@LoadBalanced
	RestTemplate ribbonRestTemplate(){
		return new RestTemplate();
	}

	@Bean("simpleRestTemplate")
	RestTemplate simpleRestTemplate(){
		return new RestTemplate();
	}
}
