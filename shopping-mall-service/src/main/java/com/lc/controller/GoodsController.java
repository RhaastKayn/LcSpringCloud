package com.lc.controller;

import com.lc.shoppingcommon.interfaces.GoodsFeignService;
import com.lc.shoppingcommon.pojo.GoodsEntity;
import com.lc.shoppingcommon.pojo.SeckillOrderEntity;
import com.lc.shoppingcommon.pojo.UserEntity;
import com.lc.shoppingcommon.request.result.SrvResult;

import java.util.List;

/**
 * @author 刘晨
 * @description
 * @create 2021/6/24 0024
 * @since 1.0.0
 */
public class GoodsController implements GoodsFeignService {
    /**
     * 根据商品Id获取商品信息
     *
     * @param goodsId
     * @return
     */
    @Override
    public SrvResult<GoodsEntity> getByGoodsId(Long goodsId) {
        return null;
    }

    /**
     * 根据用户名和商品Id集合获取订单信息
     *
     * @param userId
     * @param goodsIds
     * @return
     */
    @Override
    public SrvResult<List<SeckillOrderEntity>> getOrderByNameAndGoods(Long userId, List<Long> goodsIds) {
        return null;
    }

    /**
     * 根据商品Id获取订单信息
     *
     * @param goodsId
     * @return
     */
    @Override
    public SrvResult<SeckillOrderEntity> getOrderByGoodsId(Long goodsId) {
        return null;
    }

    /**
     * 生成秒杀url
     *
     * @param goodsId
     * @param user
     */
    @Override
    public String getSeckillPath(Long goodsId, UserEntity user) {
        return null;
    }

    /**
     * 验证url
     *
     * @param user
     * @param goodsId
     * @param path
     * @return
     */
    @Override
    public Boolean checkUrl(UserEntity user, Long goodsId, String path) {
        return null;
    }

    /**
     * 秒杀，进行写库
     *
     * @param user
     * @param goodsId
     */
    @Override
    public void doSeckill(UserEntity user, Long goodsId) {

    }
}
