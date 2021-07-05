package com.lc.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2021/4/29 0029
 * @since 1.0.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${server.port}")
    private String port;

    //获取当前服务信息
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/getInfo")
    public String getServerInfo() {
        List<String> serviceList = discoveryClient.getServices();
        if (CollectionUtils.isEmpty(serviceList)) {
            return "暂无服务";
        } else {
            return  serviceList.get(0) + ":" + port;
        }
    }
}
