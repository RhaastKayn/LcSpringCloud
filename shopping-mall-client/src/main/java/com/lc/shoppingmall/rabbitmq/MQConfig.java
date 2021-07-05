package com.lc.shoppingmall.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 刘晨
 * @description MQ config
 * @create 2021/6/8 0008
 * @since 1.0.0
 */
@Configuration
public class MQConfig {
    public static final String SECKILL_QUEUE = "seckill_queue";
    public static final String QUEUE = "queue";
    /**
     * Direct模式 交换机Exchange
     * */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }
}
