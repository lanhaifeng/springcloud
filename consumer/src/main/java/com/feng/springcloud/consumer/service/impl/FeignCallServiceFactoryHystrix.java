package com.feng.springcloud.consumer.service.impl;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

/**
 * consumer
 * feign熔断处理
 * 2019/12/28 23:31
 *
 * @author lanhaifeng
 * @version 1.0
 */
@Component
@Slf4j
public class FeignCallServiceFactoryHystrix implements FallbackFactory<FeignCallService> {

    //利用FallbackFactory熔断，可以记录导致熔断的原因
    @Override
    public FeignCallService create(Throwable cause) {
        log.error("FeignCallService服务发生熔断,错误：" + ExceptionUtils.getFullStackTrace(cause));
        return new FeignCallService() {
            @Override
            public String helloWorld(String name) {
                return "抱歉helloWorld服务无法访问,参数：" + name;
            }

            @Override
            public String notExistService() {
                return "该服务不存在，请检查服务名";
            }
        };
    }

}
