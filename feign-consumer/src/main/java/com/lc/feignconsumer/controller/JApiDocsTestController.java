package com.lc.feignconsumer.controller;

import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户接口
 */
@RequestMapping("test")
@RestController
public class JApiDocsTestController {

    /**
     * JApiDocs测试接口
     * @param docId
     * @return
     * @since 1.2
     */
    @ApiDoc
    @PostMapping("test")
    public String test(String docId){
        return "test";
    }

}

