package com.feng.springcloud.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * producer
 * 2019/12/23 14:08
 * producer服务端启动入口
 *
 * @author lanhaifeng
 * @since 1.0
 **/
@SpringBootApplication
@ComponentScan
public class ProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
}
