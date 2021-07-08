package com.lc.shoppingcommon.interfaces;


import com.lc.shoppingcommon.pojo.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 刘晨
 * @description 秒杀FeignService
 * @create 2021/5/17 0017
 * @since 1.0.0
 */
@FeignClient(value = "shopping-mall-service", contextId = "SeckillController")
public interface SeckillFeignService {
    /**
     * 生成秒杀url
     */
    @PostMapping("/seckill/getSeckillPath")
    String getSeckillPath(@RequestParam("goodsId") Long goodsId, @RequestBody UserEntity user);

    /**
     * 验证url
     * @param user
     * @param goodsId
     * @param path
     * @return
     */
    @PostMapping("/seckill/checkUrl")
    Boolean checkUrl(@RequestBody UserEntity user, @RequestParam("goodsId") Long goodsId, @RequestParam("path") String path);

    /**
     * 秒杀，进行写库
     * @param user
     * @param goodsId
     */
    @PostMapping("/seckill/doSeckill")
    void doSeckill(@RequestBody UserEntity user, @RequestParam("goodsId") Long goodsId);
}
