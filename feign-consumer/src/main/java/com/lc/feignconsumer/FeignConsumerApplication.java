package com.lc.feignconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//能够让注册中心发现，扫描到该服务，与@EnableEurekaClient作用相同，但@EnableEurekaClient只适用于Eureka作为注册中心，@EnableDiscoveryClient可以是其他注册中心
@EnableDiscoveryClient
public class FeignConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerApplication.class, args);
    }

}
