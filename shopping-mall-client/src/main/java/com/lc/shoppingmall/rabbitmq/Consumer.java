package com.lc.shoppingmall.rabbitmq;

import com.rabbitmq.client.*;
import java.io.IOException;

/**
 * @author 刘晨
 * @description rabbitMQ消息的消费者
 * @create 2021/10/20 0020
 * @since 1.0.0
 */
public class Consumer {
    public static void main(String[] args) {
        //1.创建连接工程
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        //虚拟访问节点
//        connectionFactory.setVirtualHost("/");
        //2.创建连接
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection("生产者");
            //3.通过连接获取通道channel
            channel = connection.createChannel();
            //4.通过通道，创建减缓及，声明队列，绑定关系，路由key，发送消息，接收消息
            String queueName = "queue1";
            //接收消息
            channel.basicConsume(queueName, true, new DeliverCallback() {
                @Override
                public void handle(String s, Delivery delivery) throws IOException {
                    System.out.println("接收消息成功，为：" + new String(delivery.getBody(), "UTF-8"));
                }
            }, new CancelCallback() {
                @Override
                public void handle(String s) throws IOException {
                    System.out.println("接收消息失败");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //7.关闭通道
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //8.关闭连接
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
