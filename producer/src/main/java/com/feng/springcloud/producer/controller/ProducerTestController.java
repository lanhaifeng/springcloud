package com.feng.springcloud.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * producer
 * 2019/12/27 16:58
 * producer测试控制器
 *
 * @author lanhaifeng
 * @since
 **/
@RestController
public class ProducerTestController {

	@RequestMapping(name = "/hi", method = RequestMethod.GET)
	public String hi(@RequestParam String name){
		return "hi," + name + ",I am is producer";
	}
}
