package com.lc.controller;

import com.lc.service.GoodsService;
import com.lc.service.OrderService;
import com.lc.service.SeckillService;
import com.lc.shoppingcommon.interfaces.SeckillFeignService;
import com.lc.shoppingcommon.pojo.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘晨
 * @description
 * @create 2021/5/18 0018
 * @since 1.0.0
 */
@RestController
@RequestMapping("seckill")
public class SeckillController implements SeckillFeignService {

    @Autowired
    private SeckillService seckillService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService zoodsService;

    /**
     * 获取秒杀地址
     * @param goodsId
     * @param user
     * @return
     */
    @Override
    @PostMapping("/getSeckillPath")
    public String getSeckillPath(Long goodsId, UserEntity user) {
        return seckillService.getSeckillPath(goodsId, user);
    }

    /**
     * 检查秒杀地址
     * @param user
     * @param goodsId
     * @param path
     * @return
     */
    @Override
    @PostMapping("/checkUrl")
    public Boolean checkUrl(UserEntity user, Long goodsId, String path) {
        seckillService.checkUrl(user, goodsId, path);
        return null;
    }

    /**
     * 秒杀，进行写库
     *
     * @param user
     * @param goodsId
     */
    @Override
    @PostMapping("/doSeckill")
    public void doSeckill(UserEntity user, Long goodsId) {
        //减库存，下订单
        //1.减商品表库存
        Integer ret = zoodsService.reduceStock(goodsId);
        if (ret > 0) {
            //减库存成功，下订单
//            orderService.createOrder();
        } else {
            //没有库存了，进行内存标记
        }


    }
}
