package com.feng.springcloud.consumer.service;

import com.feng.springcloud.consumer.service.impl.FeignCallService;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.feng.springcloud.consumer.constant.ConsumerConstant.EUREKA_SERVER_SERVICE_NAME;

/**
 * consumer
 * 2019/12/27 16:18
 * 测试服务间调用
 *
 * @author lanhaifeng
 * @since
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CallServiceTest {

	@Autowired
	@Qualifier("restTemplateCallService")
	private CallService restTemplateCallService;

	@Autowired
	@Qualifier("ribbonCallService")
	private CallService ribbonCallService;

	@Autowired
	private FeignCallService feignCallService;

	private String result;

	@Test
	@Ignore
	public void feignCallTest() {
		result = feignCallService.helloWorld("feign");
		Assert.assertTrue("调用服务返回非期待结果:" + result, "hi,feign,I am is producer".equals(result));
	}

	@Test
	@Ignore
	public void ribbonCallTest() {
		result = ribbonCallService.helloWorld(EUREKA_SERVER_SERVICE_NAME, "ribbon");
		Assert.assertTrue("调用服务返回非期待结果:" + result, "hi,ribbon,I am is producer".equals(result));
	}

	@Test
	@Ignore
	public void restTemplateCallTest() {
		result = restTemplateCallService.helloWorld(EUREKA_SERVER_SERVICE_NAME, "restTemplate");
		Assert.assertTrue("调用服务返回非期待结果:" + result, "hi,restTemplate,I am is producer".equals(result));
	}
}
