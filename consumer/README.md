### 服务间调用
1. Ribbon+RestTemplate
    Ribbon是一个基于HTTP和TCP客户端的负载均衡器
    
2. Feign
    其实feign也使用了ribbon, 只要使用@FeignClient时，ribbon就会自动使用
    Feign是声明式服务调用,更优雅，配合hystrix实现熔断
    
3. 纯RestTemplate
    利用eureka中DiscoveryClient通过服务名拿到服务对应前缀，类似https://127.0.0.1:9090
    使用RestTemplate直接访问restApi