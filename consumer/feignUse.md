springboot版本1.5.10熔断实现

步骤一：引入依赖

eureka中带有Feign的包
````xml
    <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
    </dependency>
````    


步骤二：配置好eureka
配置eureka服务中心
````yaml
eureka:
  client:
    healthcheck:
      enabled: true
    serviceUrl:
          defaultZone: http://eureka:eurekamchz@127.0.0.1:8761/eureka/
    fetch-registry: true
    registry-fetch-interval-seconds: 8
  instance:
    preferIpAddress: true
    instance-id: ${spring.cloud.client.ipAddress}:${spring.application.name}:${server.port}
    lease-renewal-interval-in-seconds: 4
    lease-expiration-duration-in-seconds: 12
````
开启熔断
````yaml
feign:
  hystrix:
    enabled: true
````

步骤三：声明调用的服务接口
fallback或fallbackFactory指明降级服务，两个配置一个即可
````java
@FeignClient(value = ConsumerConstant.EUREKA_SERVER_SERVICE_NAME,
//        fallback = FeignCallServiceHystrix.class, 使用fallback无法捕获错误进行错误输出
        fallbackFactory = FeignCallServiceFactoryHystrix.class
)
@Component
public interface FeignCallService {

	@RequestMapping(value = "/hi", method = RequestMethod.GET)
	String helloWorld(@RequestParam("name") String name);

    @RequestMapping(value = "/notExistService", method = RequestMethod.GET)
    String notExistService();
}
````
区别：DeviceServiceFactoryHystrix中能捕获异常进行输出处理

步骤四：提供降级服务
FeignCallServiceHystrix
````java
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

````
FeignCallServiceFactoryHystrix
````java
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
````

当调用方法失败时，会调用FeignCallService配置的fallback或fallbackFactory指明的降级服务
