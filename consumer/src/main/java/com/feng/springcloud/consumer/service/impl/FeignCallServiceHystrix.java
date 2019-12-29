package com.feng.springcloud.consumer.service.impl;

import org.springframework.stereotype.Service;

/**
 * consumer
 * 2019/12/27 16:09
 * FeignCallServiceTest服务熔断实现
 *
 * @author lanhaifeng
 * @since
 **/
@Service
public class FeignCallServiceHystrix implements FeignCallService {

	@Override
	public String helloWorld(String name) {
		return "抱歉helloWorld服务无法访问,参数：" + name;
	}

    @Override
    public String notExistService() {
        return "该服务不存在，请检查服务名";
    }
}
