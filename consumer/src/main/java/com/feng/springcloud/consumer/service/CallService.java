package com.feng.springcloud.consumer.service;

/**
 * consumer
 * 2019/12/27 15:58
 * 调用服务端测试接口
 *
 * @author lanhaifeng
 * @since
 **/
public interface CallService {

	String helloWorld(String serviceName, String name);
}
