package com.lc.shoppingmall.rabbitmq;

import com.lc.shoppingcommon.interfaces.GoodsFeignService;
import com.lc.shoppingcommon.interfaces.OrderFeignService;
import com.lc.shoppingcommon.interfaces.SeckillFeignService;
import com.lc.shoppingcommon.pojo.GoodsEntity;
import com.lc.shoppingcommon.pojo.UserEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 刘晨
 * @description 消息消费者
 * @create 2021/6/8 0008
 * @since 1.0.0
 */
@Service
public class MQReceiver {

    @Autowired
    private GoodsFeignService goodsFeignService;
    @Autowired
    private SeckillFeignService seckillFeignService;
    /**
     * 消费秒杀消息
     * @param seckillMessage
     */
    @RabbitListener(queues = MQConfig.SECKILL_QUEUE)
    public void receiverSeckillMessage(SeckillMessage seckillMessage) {
        UserEntity userEntity = seckillMessage.getUser();
        Long goodsId = seckillMessage.getGoodsId();
        //1.查看商品是否还有库存
        GoodsEntity goodsEntity = goodsFeignService.getByGoodsId(goodsId).getData();
        if (goodsEntity == null || goodsEntity.getGoodsStock() <= 0) {
            return;
        }
        //2.进行数据库操作，减库存，写订单
        seckillFeignService.doSeckill(userEntity, goodsId);
    }
}
