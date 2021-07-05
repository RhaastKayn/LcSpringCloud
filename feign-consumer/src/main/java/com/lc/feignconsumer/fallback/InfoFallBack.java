package com.lc.feignconsumer.fallback;

import com.lc.feignconsumer.service.InfoService;
import org.springframework.stereotype.Component;

/**
 *  Feign中使用Hystrix支持服务降级
 * 〈〉
 *
 * @author Administrator
 * @create 2021/5/6 0006
 * @since 1.0.0
 */
@Component
public class InfoFallBack implements InfoService {
    @Override
    public String getInfo() {
        return "getInfo fallback";
    }
}
