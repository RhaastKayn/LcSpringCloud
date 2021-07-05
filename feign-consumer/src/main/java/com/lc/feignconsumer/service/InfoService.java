package com.lc.feignconsumer.service;

import com.lc.feignconsumer.fallback.InfoFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "eureka-client", fallback = InfoFallBack.class)
//@FeignClient(value = "eureka-client")
public interface InfoService {

    @GetMapping("/test/getInfo")
    public String getInfo();
}
