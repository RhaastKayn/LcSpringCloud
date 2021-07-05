package com.lc.service;

import com.lc.shoppingcommon.pojo.SeckillOrderEntity;
import com.lc.shoppingcommon.request.result.SrvResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 刘晨
 * @description 订单service
 * @create 2021/6/3 0003
 * @since 1.0.0
 */
@Service
@Transactional
public class OrderService extends AbstractMybatisService<SeckillOrderEntity>{

    public List<SeckillOrderEntity> getOrderByNameAndGoods(Long userId, List<Long> goodsIdList) {
        Example example = new Example(SeckillOrderEntity.class);
        example.createCriteria().andEqualTo("userId", userId).andIn("goodsId", goodsIdList);
        return mapper.selectByExample(example);
    }
}
