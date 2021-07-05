package com.lc.service;


import com.lc.mapper.GoodsMapper;
import com.lc.shoppingcommon.pojo.GoodsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @author 刘晨
 * @description 商品service
 * @create 2021/6/8 0008
 * @since 1.0.0
 */
@Service
@Transactional
public class GoodsService extends AbstractMybatisService<GoodsEntity>{


    /**
     * 减商品表库存
     * @param goodsId
     */
    public Integer reduceStock(Long goodsId) {
        GoodsEntity entity = queryByProperty("goodsId", goodsId);
        if (entity == null) {
            return 0;
        }
        entity.setGoodsStock(entity.getGoodsStock() - 1);
        Example example = new Example(GoodsEntity.class);
        example.createCriteria().andEqualTo("goodsId", goodsId);
        return mapper.updateByExampleSelective(entity, example);
    }


}
