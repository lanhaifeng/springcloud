package com.feng.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * consumer
 * 2019/12/23 14:08
 * eureka服务端启动入口
 *
 * @author lanhaifeng
 * @since 1.0
 **/
@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
}
