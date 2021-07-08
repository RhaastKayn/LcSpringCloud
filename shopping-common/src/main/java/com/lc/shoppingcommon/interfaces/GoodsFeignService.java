package com.lc.shoppingcommon.interfaces;

import com.lc.shoppingcommon.pojo.GoodsEntity;
import com.lc.shoppingcommon.pojo.SeckillOrderEntity;
import com.lc.shoppingcommon.pojo.UserEntity;
import com.lc.shoppingcommon.request.result.SrvResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author 刘晨
 * @description 商品Feign
 * @create 2021/6/8 0008
 * @since 1.0.0
 */
@FeignClient(value = "shopping-mall-service", contextId = "GoodsController")
public interface GoodsFeignService {

    /**
     * 根据商品Id获取商品信息
     * @param goodsId
     * @return
     */
    @PostMapping("/goods/getByGoodsId")
    SrvResult<GoodsEntity> getByGoodsId(@RequestParam("goodsId") Long goodsId);




    /**
     * 根据用户名和商品Id集合获取订单信息
     * @param userId
     * @param goodsIds
     * @return
     */
    @PostMapping("/order/getOrderByNameAndGoods")
    SrvResult<List<SeckillOrderEntity>> getOrderByNameAndGoods(@RequestParam("userId") Long userId, @RequestBody List<Long> goodsIds);

    /**
     * 根据商品Id获取订单信息
     * @param goodsId
     * @return
     */
    @PostMapping("/order/getOrderByGoodsId")
    SrvResult<SeckillOrderEntity> getOrderByGoodsId(@RequestParam("goodsId") Long goodsId);

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
