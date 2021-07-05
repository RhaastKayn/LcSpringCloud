package com.lc.shoppingmall.rabbitmq;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author 刘晨
 * @description 消息发送者
 * @create 2021/6/7 0007
 * @since 1.0.0
 */
@Service
public class MQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 消息队列 -- 发送秒杀消息
     * @param seckillMessage
     */
    public void sendSeckillMessage(SeckillMessage seckillMessage) {
        amqpTemplate.convertAndSend(MQConfig.SECKILL_QUEUE, seckillMessage);
    }

}
