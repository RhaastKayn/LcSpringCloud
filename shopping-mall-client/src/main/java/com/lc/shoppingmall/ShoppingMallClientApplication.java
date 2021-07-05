package com.lc.shoppingmall;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@EnableCircuitBreaker//集成Hystrix
@EnableRabbit
@EnableFeignClients(basePackages = { "com.lc.shoppingcommon" })
//@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)//集成JMX监控
@EnableDiscoveryClient
public class ShoppingMallClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingMallClientApplication.class, args);
    }

}
