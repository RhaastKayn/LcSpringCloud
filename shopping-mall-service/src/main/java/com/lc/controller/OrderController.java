package com.lc.controller;

import com.lc.service.OrderService;
import com.lc.shoppingcommon.interfaces.OrderFeignService;
import com.lc.shoppingcommon.pojo.SeckillOrderEntity;
import com.lc.shoppingcommon.request.result.SrvResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description 订单ServiceController
 * @author 刘晨
 * @create 2021/6/3 0003
 * @since 1.0.0
 */
@RestController
@RequestMapping("/order")
public class OrderController implements OrderFeignService{
    @Autowired
    private OrderService orderService;

    /**
     * 根据用户名和商品Id集合获取订单信息
     * @param userId
     * @param goodsIdList
     * @return
     */
    @Override
    @PostMapping("/getOrderByNameAndGoods")
    public SrvResult<List<SeckillOrderEntity>> getOrderByNameAndGoods(Long userId, List<Long> goodsIdList) {
        return SrvResult.creat(true, orderService.getOrderByNameAndGoods(userId, goodsIdList));
    }

    /**
     * 根据商品Id获取订单信息
     * @param goodsId
     * @return
     */
    @Override
    @PostMapping("/getOrderByGoodsId")
    public SrvResult<SeckillOrderEntity> getOrderByGoodsId(Long goodsId) {
        return SrvResult.creat(true, orderService.queryByProperty("goodsId", goodsId));
    }
}
