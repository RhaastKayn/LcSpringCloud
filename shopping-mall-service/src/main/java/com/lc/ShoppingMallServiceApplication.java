package com.lc;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.lc.shoppingcommon.util.MyMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

//多线程开关
//@EnableAsync
@SpringBootApplication
//@EnableEurekaClient//服务注册
@EnableDiscoveryClient//服务发现
//com.lc.mapper包下的所有实现MyMapper的接口在编译后生成相应的实现类。多个包的话 basePackages={"com.lc.mapper", "com.lc.testMapper"}
@MapperScan(basePackages="com.lc.mapper")
@EnableTransactionManagement//开启事务支持
//@EnableCircuitBreaker //对hystrixR熔断机制的支持
@EnableFeignClients(basePackages = { "com.lc.shoppingcommon" })
//@Import(FdfsClientConfig.class)//导入配置类或者一些需要前置加载的类
//@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)   //集成JMX监控
public class ShoppingMallServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingMallServiceApplication.class, args);
    }

}
