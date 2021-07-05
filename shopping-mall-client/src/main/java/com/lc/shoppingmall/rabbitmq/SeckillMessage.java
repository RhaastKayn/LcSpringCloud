package com.lc.shoppingmall.rabbitmq;

import com.lc.shoppingcommon.pojo.UserEntity;
import lombok.Data;

/**
 * @author 刘晨
 * @description 秒杀消息
 * @create 2021/6/7 0007
 * @since 1.0.0
 */
@Data
public class SeckillMessage {
    private UserEntity user;
    private Long goodsId;
}
