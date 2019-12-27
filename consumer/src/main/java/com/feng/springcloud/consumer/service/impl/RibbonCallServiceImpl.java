package com.feng.springcloud.consumer.service.impl;

import com.feng.springcloud.consumer.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * consumer
 * 2019/12/27 16:00
 * ribbon实现服务调用
 *
 * @author lanhaifeng
 * @since
 **/
@Service("ribbonCallService")
public class RibbonCallServiceImpl implements CallService {

	@Autowired
	@Qualifier("ribbonRestTemplate")
	private RestTemplate restTemplate;

	@Override
	public String helloWorld(String serviceName, String name) {
		return restTemplate.getForObject("http://" + serviceName + "/hi?name=" + name, String.class);
	}
}
