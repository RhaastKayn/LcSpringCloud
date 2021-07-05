package com.lc.feignconsumer.controller;

import com.lc.feignconsumer.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 * @author Administrator
 * @create 2021/5/6 0006
 * @since 1.0.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private InfoService infoService;

    /**
     * 获取信息接口
     * @return
     */
    @GetMapping("/getInfo")
    public String getInfo(){
        return infoService.getInfo();
    }

    /**
     * 测试接口
     * @return
     */
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
