package com.feng.springcloud.consumer.service.impl;

import com.feng.springcloud.consumer.service.CallService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * consumer
 * 2019/12/27 16:00
 * ribbon实现服务调用
 *
 * @author lanhaifeng
 * @since
 **/
@Service("restTemplateCallService")
@Slf4j
public class RestTemplateCallServiceImpl implements CallService {

	@Resource(name = "simpleRestTemplate")
	private RestTemplate restTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	public String helloWorld(String serviceName, String name) {
		try{
			List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
			return restTemplate.getForObject(serviceInstances.get(0).getUri().toString() + "/hi?name=" + name, String.class);
		}catch(Exception e){
		    log.error("" + ExceptionUtils.getFullStackTrace(e));
			return "抱歉" + name + ",服务" + serviceName + "无法访问";
		}
	}
}
