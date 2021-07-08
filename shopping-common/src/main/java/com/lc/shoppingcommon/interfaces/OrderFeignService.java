package com.lc.shoppingcommon.interfaces;

import com.lc.shoppingcommon.pojo.SeckillOrderEntity;
import com.lc.shoppingcommon.request.result.SrvResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 订单Feign
 */
@FeignClient(value = "shopping-mall-service", contextId = "OrderController")
public interface OrderFeignService {

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
}
